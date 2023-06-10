package com.example.lb_app;

public class Expenditure {
    private String ID;
    private String Trans_ID;
    private String Date;
    private String Description;
    private String Amount;
    private String Price;
    private String Total;
    private String Notes;

    public Expenditure(String ID, String Trans_ID, String Date, String Description, String Amount, String Price, String Total, String Notes){
        this.setNotes(Notes);
        this.setID(ID);
        this.setTrans_ID(Trans_ID);
        this.setDate(Date);
        this.setDescription(Description);
        this.setAmount(Amount);
        this.setPrice(Price);
        this.setTotal(Total);

    }

    public Expenditure(){

    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTrans_ID() {
        return Trans_ID;
    }

    public void setTrans_ID(String trans_ID) {
        Trans_ID = trans_ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }


    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
