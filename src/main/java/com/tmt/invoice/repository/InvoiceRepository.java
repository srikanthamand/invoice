package com.tmt.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmt.invoice.entity.Invoice;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>{
	
	List<Invoice> findByStatus(String status);
	
	Invoice findByInvoiceNumber(String invoiceNumber);
	
}
