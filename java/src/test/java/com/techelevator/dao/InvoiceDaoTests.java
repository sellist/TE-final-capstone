package com.techelevator.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.techelevator.model.Invoice;
import com.techelevator.model.User;
import com.techelevator.model.dto.NewInvoiceDto;
import com.techelevator.model.dto.PaymentDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestingDatabaseConfig.class)
public class InvoiceDaoTests extends BaseDaoTests{
    private JdbcInvoiceDao sut;
    private NewInvoiceDto NEW_INVOICE_DTO;

    @Autowired
    protected DataSource dataSource;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcInvoiceDao(jdbcTemplate);

        LocalDate today = LocalDate.now();
        NEW_INVOICE_DTO = new NewInvoiceDto(1,
                2,
                3,
                1000,
                Date.valueOf(LocalDate.now().plusDays(30)));


    }

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

    @Test
    public void create_invoice_creates_database_entry(){
        Invoice newInvoice = sut.createInvoice(NEW_INVOICE_DTO);
        Assert.assertNotNull(newInvoice);
    }

    @Test
    public void current_date_created_when_created() {
        Invoice newInvoice = sut.createInvoice(NEW_INVOICE_DTO);
        Assert.assertEquals(newInvoice.getCreatedDate(), Date.valueOf(LocalDate.now()));
    }

    @Test
    public void get_invoices_by_landlord() {
        List<Invoice> returned = sut.getAllInvoicesByLandlordId(7);
        Assert.assertEquals(2, returned.size());
    }

    @Test
    public void get_invoices_by_renter_id() {
        List<Invoice> returned = sut.getAllInvoicesByRenterId(5);
        Assert.assertEquals(2, returned.size());
    }

    @Test
    public void get_invoice_by_id() {
        Invoice returnedInvoice = sut.getInvoiceById(1);
        System.out.println(returnedInvoice);

    }

    @Test
    public void created_invoice_stores_correct_data(){
        Invoice newInvoice = sut.createInvoice(NEW_INVOICE_DTO);
        Assert.assertEquals(NEW_INVOICE_DTO.getDueDate(),Date.valueOf(LocalDate.now().plusDays(30)));
        Assert.assertEquals(NEW_INVOICE_DTO.getRenterId(), newInvoice.getRenterId());
        Assert.assertEquals(NEW_INVOICE_DTO.getLandlordId(), newInvoice.getLandlordId());
        Assert.assertEquals(NEW_INVOICE_DTO.getAmountDue(), newInvoice.getAmountDue());
        Assert.assertEquals(NEW_INVOICE_DTO.getPropertyId(), newInvoice.getPropertyId());
        Assert.assertEquals(newInvoice.getCreatedDate(), Date.valueOf(LocalDate.now()));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void throws_exception_when_creating_with_null_date(){
        sut.createInvoice(new NewInvoiceDto(1,2,3,5,null));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void throws_exception_when_creating_with_invalid_property_id(){
        sut.createInvoice(new NewInvoiceDto(-1,2,3,5,Date.valueOf(LocalDate.now().plusDays(30))));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void throws_exception_when_creating_with_invalid_landlord_id(){
        sut.createInvoice(new NewInvoiceDto(1,-1,3,5,Date.valueOf(LocalDate.now().plusDays(30))));
    }

    @Test
    public void returns_null_on_null_dto() {
        Assert.assertNull(sut.createInvoice(null));
    }

    @Test
    public void updateInvoice_updates_invoice_in_database() {
        Invoice invoiceToUpdate = sut.createInvoice(NEW_INVOICE_DTO);
        PaymentDto dto = new PaymentDto(499, Date.valueOf(LocalDate.now()));
        sut.updateInvoice(dto,invoiceToUpdate.getInvoiceId());

        Invoice updatedInvoice = sut.getInvoiceById(invoiceToUpdate.getInvoiceId());
        System.out.println(invoiceToUpdate);
        System.out.println(updatedInvoice);
        Assert.assertEquals(501, updatedInvoice.getStatus());
        Assert.assertEquals(updatedInvoice.getPaidDate(), Date.valueOf(LocalDate.now()));
    }
}
