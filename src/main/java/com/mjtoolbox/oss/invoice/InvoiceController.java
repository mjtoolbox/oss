package com.mjtoolbox.oss.invoice;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class InvoiceController {

    @Resource
    InvoiceRepository invoiceRepository;

    @GetMapping("/invoices")
    public List<Invoice> retrieveAllInvoices() {
        return StreamSupport.stream(invoiceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/invoices/{invoice_number}")
    public Invoice findById(@PathVariable long invoice_number) {
        return invoiceRepository.findById(invoice_number)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + invoice_number));
    }
    
    @PostMapping("/invoices")
    public Invoice createInvoice(@Valid @RequestBody Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @PutMapping("/invoices/{invoice_number}")
    public Invoice updateInvoice(@PathVariable long invoice_number, @Valid @RequestBody Invoice invoice) {
        Invoice invoiceFromDB = invoiceRepository.findById(invoice_number)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + invoice_number));
        invoiceFromDB.setInvoice_date(invoice.getInvoice_date());
        return invoiceRepository.save(invoiceFromDB);
    }

    @DeleteMapping("/invoices/{invoice_number}")
    public void delete(@PathVariable long invoice_number) {
        invoiceRepository.findById(invoice_number)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + invoice_number));
        invoiceRepository.deleteById(invoice_number);
    }
}
