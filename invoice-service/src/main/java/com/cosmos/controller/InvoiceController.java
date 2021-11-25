package com.cosmos.controller;

import com.cosmos.pojo.Invoice;
import com.cosmos.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{mobileNumber}")
    public Invoice getInvoiceByMobileNumber(@PathVariable Long mobileNumber){
        return invoiceService.getInvoiceByMobileNumber(mobileNumber);
    }
}
