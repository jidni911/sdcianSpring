package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jidnivai.sdcian.sdcian.service.JasperService;


@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    JasperService jasperService;

    @GetMapping()
    public String getHomePage() {
        try {
            return "index.html"; // You can also return data or objects, depending on your use case
        } catch (Exception e) {
            System.out.println("InvoiceController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/download/{format}")
    public ResponseEntity<byte[]> downloadReport(@PathVariable String format) {
        try {
            return jasperService.downloadReport(format);
        } catch (Exception e) {
            System.out.println("InvoiceController: " + e.getMessage());
            return null;
        }
    }

}
