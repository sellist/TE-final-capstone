package com.techelevator.controller;

import com.techelevator.dao.InvoiceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Invoice;
import com.techelevator.model.dto.NewInvoiceDto;
import com.techelevator.model.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class InvoiceController {

    @Autowired
    private InvoiceDao dao;
    @Autowired
    private UserDao userDao;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/invoices/landlord/{id}")
    public List<Invoice> getAllInvoicesByLandlord(Principal principal) {
        String username = principal.getName();
        int landlordID = userDao.findIdByUsername(username);
        return dao.getAllInvoicesByLandlordId(landlordID);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/invoices/renter/{id}")
    public List<Invoice> getAllInvoicesByRenter(Principal principal) {
        String username = principal.getName();
        int renterId = userDao.findIdByUsername(username);
        return dao.getAllInvoicesByRenterId(renterId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/invoices/{id}")
    public Invoice getInvoiceById(@PathVariable int id) {
        return dao.getInvoiceById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/invoices/create")
        public Invoice createInvoice(@Valid @RequestBody NewInvoiceDto invoiceDto) {
        
            return dao.createInvoice(invoiceDto);
        }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/invoices/update/{id}")
    public void updateInvoice(@Valid @PathVariable int id, @RequestBody PaymentDto paymentDto) {

    }




}
