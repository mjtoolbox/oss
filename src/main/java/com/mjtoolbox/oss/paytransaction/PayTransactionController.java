package com.mjtoolbox.oss.paytransaction;

import com.mjtoolbox.oss.invoice.Invoice;
import com.mjtoolbox.oss.invoice.InvoiceRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * No update and delete. Payment Transaction must be kept.
 */
@CrossOrigin()
@RestController
public class PayTransactionController {

    @Resource
    PayTransactionRepository payTransactionRepository;

    @Resource
    InvoiceRepository invoiceRepository;

    @GetMapping("/paytransactions")
    public List<PayTransaction> retrieveAllPayTransactions() {
        return StreamSupport.stream(payTransactionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/invoices/{invoice_number}/paytransactions")
    public List<PayTransaction> retrieveTransactionsByInvoice(@PathVariable long invoice_number) {
        Invoice invoice = invoiceRepository.findById(invoice_number)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + invoice_number));

        return StreamSupport.stream(payTransactionRepository.findByInvoice(invoice).spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/invoices/{invoice_number}/paytransactions/{transaction_id}")
    public PayTransaction findById(@PathVariable long invoice_number, @PathVariable long transaction_id) {

        // Optional - validate if Invoice exists
        invoiceRepository.findById(invoice_number)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + invoice_number));

        // After validated, retrieve Pay Transaction by ID
        return payTransactionRepository.findById(transaction_id)
                .orElseThrow(() -> new ResourceNotFoundException("Pay transaction not found with ID: " + transaction_id));
    }

    // Create Pay transaction for the invoice
    @PostMapping("/invoices/{invoice_number}/paytransactions")
    public PayTransaction createPayTransaction(@PathVariable long invoice_number, @Valid @RequestBody PayTransaction payTransaction) {
        return invoiceRepository.findById(invoice_number).map(invoice -> {
            payTransaction.setInvoice(invoice);
            // Manually invoice number needs to be set. Otherwise it will be empty.
            payTransaction.setInvoice_number(invoice.getInvoice_number());
            return payTransactionRepository.save(payTransaction);
        }).orElseThrow(() -> new ResourceNotFoundException("Invoice ID " + invoice_number + " not found"));
    }

}
