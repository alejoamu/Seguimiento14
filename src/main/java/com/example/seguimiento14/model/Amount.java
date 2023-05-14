package com.example.seguimiento14.model;

import java.util.Date;

public class Amount {
    private double amount;
    private String description;
    private Date date;
    private AmountType amountType;

    public Amount(double amount, String description ,Date date, AmountType amountType) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.amountType = amountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }
}
