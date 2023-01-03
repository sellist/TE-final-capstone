package com.techelevator.dao;

import com.techelevator.model.Invoice;
import com.techelevator.model.dto.NewInvoiceDto;
import com.techelevator.model.dto.PaymentDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class  JdbcInvoiceDao implements InvoiceDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcInvoiceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Invoice> getAllInvoicesByLandlordId(int landlordId) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoice WHERE landlord_id = ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, landlordId);
        while (results.next()) {
            invoices.add(mapRowToInvoice(results));
        }
        return invoices;
    }

    @Override
    public List<Invoice> getAllInvoicesByRenterId(int renterId) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoice WHERE renter_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, renterId);
        while(results.next()) {
            invoices.add(mapRowToInvoice(results));
        }
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(int invoiceId) {
        Invoice invoice = null;
        String sql = "SELECT * FROM invoice WHERE invoice_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, invoiceId);
        if(results.next()) {
            invoice = mapRowToInvoice(results);
        }
        return invoice;
    }

    @Override
    public Invoice createInvoice(NewInvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }
        String sql = "INSERT INTO invoice(property_id, landlord_id, renter_id, amount_due, due_date)" +
                "VALUES (?, ?, ?, ?, ?) " +
                "RETURNING invoice_id;";
        Integer newInvoiceId = jdbcTemplate.queryForObject(sql, Integer.class,
                                                invoiceDto.getPropertyId(), invoiceDto.getLandlordId(), invoiceDto.getRenterId(),
                                                invoiceDto.getAmountDue(), invoiceDto.getDueDate());
        Invoice newInvoice = getInvoiceById(newInvoiceId);
        return newInvoice;
    }

    @Override
    public void updateInvoice(PaymentDto paymentDto, int invoiceId) {
        String sql = "UPDATE invoice SET amount_paid = ?, paid_date = ? WHERE invoice_id = ?;";
        jdbcTemplate.update(sql, paymentDto.getAmountPaid(), paymentDto.getPaidDate(), invoiceId);
    }


    public Invoice mapRowToInvoice (SqlRowSet results) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(results.getInt("invoice_id"));
        invoice.setPropertyId(results.getInt("property_id"));
        invoice.setLandlordId(results.getInt("landlord_id"));
        invoice.setRenterId(results.getInt("renter_id"));
        invoice.setAmountDue(results.getInt("amount_due"));
        invoice.setAmountPaid(results.getInt("amount_paid"));
        invoice.setStatus(results.getInt("status"));
        invoice.setCreatedDate(results.getDate("created_date"));
        invoice.setPaidDate(results.getDate("paid_date"));
        invoice.setDueDate(results.getDate("due_date"));
        return invoice;
    }
}


