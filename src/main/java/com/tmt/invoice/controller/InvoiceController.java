package com.tmt.invoice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.invoice.entity.Invoice;
import com.tmt.invoice.exception.ResourceNotFoundException;
import com.tmt.invoice.repository.InvoiceRepository;


@RestController
@RequestMapping("/api")
public class InvoiceController {
	
	 private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	 
	@Autowired
	InvoiceRepository invoiceRepository;
	
	
	// Get All Invoices
	@GetMapping("/invoices")
	public List<Invoice> getAllInvoices() {
	    return invoiceRepository.findAll();
	}
	
	
	// Create a new Invoice
	@PostMapping("/invoices")
	public Invoice createInvoice(@Valid @RequestBody Invoice invoice) {
	    return invoiceRepository.save(invoice);
	}
	
	// Get a Single Invoice
	@GetMapping("/invoices/{id}")
	public Invoice getInvoiceById(@PathVariable(value = "id") String invoiceId) {
		Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceId);
	    if(invoice == null) {
			Optional<Invoice> optionalInvoice = Optional.empty();
			optionalInvoice.orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));
		}
	    logger.info("GET API - invoice " + invoiceId);
	    return invoice;
	}
	
	// Update a Invoice
	@PutMapping("/invoices/{id}")
	public Invoice updateInvoice(@PathVariable(value = "id") String invoiceId,
	                                        @RequestBody Invoice invoiceDetails) {

		Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceId);
		if(invoice == null) {
			Optional<Invoice> optionalInvoice = Optional.empty();
			optionalInvoice.orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));
		}
		
		if(!StringUtils.isEmpty(invoiceDetails.getPaymentTerm())) {
			invoice.setPaymentTerm(invoiceDetails.getPaymentTerm());
		} 
		if(!StringUtils.isEmpty(invoiceDetails.getStatus())) {
			invoice.setStatus(invoiceDetails.getStatus());
		}

		Invoice updatedPaymentTerm = invoiceRepository.save(invoice);
	    return updatedPaymentTerm;
	}
	
	// Delete a Invoice
	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<?> deleteInvoice(@PathVariable(value = "id") String invoiceId) {
		Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceId);
		if(invoice == null) {
			Optional<Invoice> optionalInvoice = Optional.empty();
			optionalInvoice.orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));
		}
		
		invoiceRepository.delete(invoice);
	    return ResponseEntity.ok().build();
	}
	
}
