package com.techelevator.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class PaymentDto {

    private int amountPaid;
    private Date paidDate;

    public PaymentDto(int amountPaid, Date paidDate) {
        this.amountPaid = amountPaid;
        this.paidDate = paidDate;
    }

    public PaymentDto() {
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }
}
