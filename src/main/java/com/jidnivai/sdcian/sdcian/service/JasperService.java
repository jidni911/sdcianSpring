package com.jidnivai.sdcian.sdcian.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.Memo;
import com.jidnivai.sdcian.sdcian.entity.shop.MemoItem;
import com.jidnivai.sdcian.sdcian.entity.shop.Order;
import com.jidnivai.sdcian.sdcian.entity.shop.OrderItem;
import com.jidnivai.sdcian.sdcian.payload.OrderItemField;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class JasperService {

    public ResponseEntity<byte[]> downloadReport(String format) throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = null;
        Map<String, Object> parameters = createParameters();

        JasperPrint jasperPrint = createJasperPrint(beanCollectionDataSource, parameters);

        byte[] data;
        HttpHeaders headers = new HttpHeaders();

        switch (format.toLowerCase()) {
        case "pdf":
            data = exportToPdf(jasperPrint);
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=report.pdf");
            break;

        case "html":
            data = exportToHtml(jasperPrint);
            headers.setContentType(MediaType.TEXT_HTML);
            headers.add("Content-Disposition", "inline; filename=report.html");
            break;

        case "xlsx":
            data = exportToXlsx(jasperPrint);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition", "attachment; filename=report.xlsx");
            break;

        case "csv":
            data = exportToCsv(jasperPrint);
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.add("Content-Disposition", "attachment; filename=report.csv");
            break;

        case "docx":
            data = exportToDocx(jasperPrint);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition", "attachment; filename=report.docx");
            break;

        default:
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok().headers(headers).body(data);
    }

    public ResponseEntity<byte[]> memo(Order order, List<OrderItem> orderItems) throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = createDataSource(orderItems);
        Map<String, Object> parameters = createParameters(order, orderItems.get(0).getSeller());

        JasperPrint jasperPrint = createJasperPrint(beanCollectionDataSource, parameters);

        byte[] data;
        HttpHeaders headers = new HttpHeaders();
        data = exportToHtml(jasperPrint);
        headers.setContentType(MediaType.TEXT_HTML);
        headers.add("Content-Disposition", "inline; filename=report.html");
        return ResponseEntity.ok().headers(headers).body(data);
    }



    private JRBeanCollectionDataSource createDataSource(List<OrderItem> orderItems) {
        
        return new JRBeanCollectionDataSource(orderItems.stream().map(
            (OrderItem orderItem) -> { 
                OrderItemField orderItemField = new OrderItemField();
                orderItemField.setProductDetails(orderItem.getProduct().getName());
                orderItemField.setUnitPrice(orderItem.getPrice());
                orderItemField.setQuantity(orderItem.getQuantity());
                orderItemField.setTotalPrice(orderItem.getPrice() * orderItem.getQuantity());
                orderItemField.setOrderId(orderItem.getOrder().getId());
                return orderItemField;
            }
            ).collect(Collectors.toList()), false);
    }

    private Map<String, Object> createParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("total", "7000");
        return parameters;
    }

    private Map<String, Object> createParameters(Order order, User seller) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Total", order.getTotal());
        parameters.put("SubTotal", order.getSubTotal());
        parameters.put("shopName", seller.getFullName());
        parameters.put("shopAddress", seller.getAddress());
        parameters.put("shopNumber", seller.getPhoneNumber());
        parameters.put("shopMail", seller.getEmail());
        parameters.put("customerName", order.getUserName());
        parameters.put("customerNumber", order.getPhoneNumber());
        parameters.put("customerAddress", order.getAddress());
        parameters.put("orderNumber", order.getId());
        parameters.put("serviceCharge", order.getServiceCharge());
        parameters.put("deliveryCharge", order.getDeliveryCharge());
        parameters.put("discount", order.getDiscount());
        return parameters;
    }

    private JasperPrint createJasperPrint(JRBeanCollectionDataSource dataSource, Map<String, Object> parameters)
            throws JRException, FileNotFoundException {
        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/memo.jrxml"));
        // .compileReport(new FileInputStream("src/main/resources/invoice.jrxml"));
        return JasperFillManager.fillReport(compileReport, parameters, dataSource);
    }

    private byte[] exportToPdf(JasperPrint jasperPrint) throws JRException {
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private byte[] exportToHtml(JasperPrint jasperPrint) throws JRException, IOException {
        ByteArrayOutputStream htmlOutput = new ByteArrayOutputStream();
        HtmlExporter htmlExporter = new HtmlExporter();
        htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(htmlOutput));
        htmlExporter.exportReport();
        return htmlOutput.toByteArray();
    }

    private byte[] exportToXlsx(JasperPrint jasperPrint) throws JRException, IOException {
        ByteArrayOutputStream xlsxOutput = new ByteArrayOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsxOutput));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        return xlsxOutput.toByteArray();
    }

    private byte[] exportToCsv(JasperPrint jasperPrint) throws JRException, IOException {
        ByteArrayOutputStream csvOutput = new ByteArrayOutputStream();
        JRCsvExporter csvExporter = new JRCsvExporter();
        csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        csvExporter.setExporterOutput(new SimpleWriterExporterOutput(csvOutput));
        SimpleCsvExporterConfiguration csvConfig = new SimpleCsvExporterConfiguration();
        csvExporter.setConfiguration(csvConfig);
        csvExporter.exportReport();
        return csvOutput.toByteArray();
    }

    private byte[] exportToDocx(JasperPrint jasperPrint) throws JRException, IOException {
        ByteArrayOutputStream docxOutput = new ByteArrayOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(docxOutput));
        docxExporter.exportReport();
        return docxOutput.toByteArray();
    }

    public ResponseEntity<byte[]> makeMemo(Memo memo)  throws JRException, IOException {
        //(Order order, List<OrderItem> orderItems)
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(memo.getMemoItems().stream().map(
            (MemoItem memoItem) -> { 
                OrderItemField orderItemField = new OrderItemField();
                orderItemField.setProductDetails(memoItem.getProduct().getName());
                orderItemField.setUnitPrice(memoItem.getPrice());
                orderItemField.setQuantity(memoItem.getQuantity());
                orderItemField.setTotalPrice(memoItem.getPrice() * memoItem.getQuantity());
                orderItemField.setOrderId(memo.getId());
                return orderItemField;
            }
            ).collect(Collectors.toList()), false);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Total", memo.getTotal());
        parameters.put("SubTotal", memo.getSubtotal());
        parameters.put("shopName", memo.getSeller().getFullName());
        parameters.put("shopAddress", memo.getSeller().getAddress());
        parameters.put("shopNumber", memo.getSeller().getPhoneNumber());
        parameters.put("shopMail", memo.getSeller().getEmail());
        parameters.put("customerName", memo.getBuyerName());
        parameters.put("customerNumber", memo.getBuyerPhoneNumber());
        parameters.put("customerAddress", memo.getBuyerAddress());
        parameters.put("orderNumber", memo.getMemoNumber());
        parameters.put("serviceCharge", memo.getServiceCharge());
        parameters.put("deliveryCharge", memo.getDeliveryCharge());
        parameters.put("discount", memo.getDiscount());

        JasperPrint jasperPrint = createJasperPrint(beanCollectionDataSource, parameters);

        byte[] data;
        HttpHeaders headers = new HttpHeaders();
        data = exportToHtml(jasperPrint);
        headers.setContentType(MediaType.TEXT_HTML);
        headers.add("Content-Disposition", "inline; filename=report.html");
        return ResponseEntity.ok().headers(headers).body(data);
    }


}

    
