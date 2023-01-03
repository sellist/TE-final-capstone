package com.techelevator.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class NewInvoiceDto {

    private int propertyId;
    private int landlordId;
    private int renterId;
    private int amountDue;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date dueDate;

    public NewInvoiceDto(int propertyId, int landlordId, int renterId, int amountDue, Date dueDate) {
        this.propertyId = propertyId;
        this.landlordId = landlordId;
        this.renterId = renterId;
        this.amountDue = amountDue;
        this.dueDate = dueDate;
    }

    public NewInvoiceDto() {
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
