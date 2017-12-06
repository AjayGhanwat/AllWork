package com.bridgelabz.fundoopay.home.model;

public class outstandingBills {

    private String billsDate;
    private String billsTime;
    private String billsTotal;

    public outstandingBills() {
    }

    public outstandingBills(String billsDate, String billsTime, String billsTotal) {
        this.billsDate = billsDate;
        this.billsTime = billsTime;
        this.billsTotal = billsTotal;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getBillsTime() {
        return billsTime;
    }

    public void setBillsTime(String billsTime) {
        this.billsTime = billsTime;
    }

    public String getBillsTotal() {
        return billsTotal;
    }

    public void setBillsTotal(String billsTotal) {
        this.billsTotal = billsTotal;
    }
}
