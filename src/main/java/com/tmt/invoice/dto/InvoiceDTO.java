package com.tmt.invoice.dto;

public class InvoiceDTO {
	
    private String invoiceNumber;
    private String paymentTerm;
    private String status;
    private String invoiceDate;


	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


    
	@Override
    public String toString() {
        return "Invoice [paymentTerm=" + paymentTerm + ", status=" + status  + ", invoiceDate =" + invoiceDate
        		 + "]";
    }

}
