package com.example.lb_app;

public class Harvest {
    private String ID;
    private String Hive_ID;
    private String Date;
    private String Amount_H;
    private String Other;
    private String Amount_O;
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

    public String getAmount_H() {
        return Amount_H;
    }

    public void setAmount_H(String amount_H) {
        Amount_H = amount_H;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public String getAmount_O() {
        return Amount_O;
    }

    public void setAmount_O(String amount_O) {
        Amount_O = amount_O;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
