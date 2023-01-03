package com.techelevator.model;

import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Invoice {
    private int invoiceId;
    private int propertyId;
    private int landlordId;
    private int renterId;

    private int amountDue;
    private int amountPaid; //nullable
    private int status;

    private Date createdDate;
    private Date paidDate; //nullable
    private Date dueDate;

    public Invoice() {

    }

    public Invoice(int invoiceId, int propertyId, int landlordId, int renterId, int amountDue, int amountPaid, int status, Date createdDate, Date paidDate, Date dueDate) {
        this.invoiceId = invoiceId;
        this.propertyId = propertyId;
        this.landlordId = landlordId;
        this.renterId = renterId;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.status = status;
        this.createdDate = createdDate;
        this.paidDate = paidDate;
        this.dueDate = dueDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", propertyId=" + propertyId +
                ", landlordId=" + landlordId +
                ", renterId=" + renterId +
                ", amountDue=" + amountDue +
                ", amountPaid=" + amountPaid +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", paidDate=" + paidDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
