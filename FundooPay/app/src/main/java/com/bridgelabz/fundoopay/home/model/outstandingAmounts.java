package com.bridgelabz.fundoopay.home.model;

public class outstandingAmounts {

    String amountDate;
    String amountTime;
    String amountTotal;

    public outstandingAmounts() {
    }

    public outstandingAmounts(String amountDate, String amountTime, String amountTotal) {
        this.amountDate = amountDate;
        this.amountTime = amountTime;
        this.amountTotal = amountTotal;
    }

    public String getAmountDate() {
        return amountDate;
    }

    public void setAmountDate(String amountDate) {
        this.amountDate = amountDate;
    }

    public String getAmountTime() {
        return amountTime;
    }

    public void setAmountTime(String amountTime) {
        this.amountTime = amountTime;
    }

    public String getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(String amountTotal) {
        this.amountTotal = amountTotal;
    }
}
