package com.example.lb_app;

public class Hives {
    private String ID;
    private String HiveID;
    private String Date;
    private String Hive_Stat;
    private String Frames;
    private String Population;
    private String Location;
    private String General_Stat;
    private String Notes;

    public Hives(){

    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHiveID() {
        return HiveID;
    }

    public void setHiveID(String hiveID) {
        HiveID = hiveID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHive_Stat() {
        return Hive_Stat;
    }

    public void setHive_Stat(String hive_Stat) {
        Hive_Stat = hive_Stat;
    }

    public String getFrames() {
        return Frames;
    }

    public void setFrames(String frames) {
        Frames = frames;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getLocation() {
        return Location;
    }

    public String getGeneral_Stat() {return General_Stat;}

    public void setGeneral_Stat(String general_Stat) {General_Stat = general_Stat;}

    public void setLocation(String location) {
        Location = location;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }


}
