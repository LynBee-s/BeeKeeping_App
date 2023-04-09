package com.example.lb_app;

public class Harvest {
    private String ID;
    private String Hive_ID;
    private String Date;
    private String Amount;
    private String Other;
    private String O_Amount;
    private String Notes;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHive_ID() {
        return Hive_ID;
    }

    public void setHive_ID(String hive_ID) {
        Hive_ID = hive_ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public String getO_Amount() {
        return O_Amount;
    }

    public void setO_Amount(String o_Amount) {
        O_Amount = o_Amount;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
