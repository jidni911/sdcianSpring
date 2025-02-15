package com.jidnivai.sdcian.sdcian.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jidnivai.sdcian.sdcian.service.JasperService;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    JasperService jasperService;

    @GetMapping()
    public String getHomePage() {
        return "index.html"; // You can also return data or objects, depending on your use case
    }

    @GetMapping(value = "/download/{format}")
    public ResponseEntity<byte[]> downloadReport(@PathVariable String format) throws JRException, IOException {
        return jasperService.downloadReport(format);
    }

    

}