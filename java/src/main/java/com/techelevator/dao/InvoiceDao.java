package com.techelevator.dao;

import com.techelevator.model.Invoice;
import com.techelevator.model.dto.NewInvoiceDto;
import com.techelevator.model.dto.PaymentDto;

import java.util.List;

public interface InvoiceDao {

    List<Invoice> getAllInvoicesByLandlordId(int landlordId);

    List<Invoice> getAllInvoicesByRenterId(int renterId);

    Invoice getInvoiceById(int invoiceId);

    Invoice createInvoice(NewInvoiceDto invoiceDto);

    void updateInvoice(PaymentDto paymentDto, int invoiceId);


}
