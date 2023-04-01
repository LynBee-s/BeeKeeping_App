package com.example.lb_app;
public class Hives {
    private String ID;
    private String HiveID;
    private String Date;
    private String Hive_Stat;
    private String Frames;
    private String Population;
    private String Location;
    private String Notes;

    public Hives(String ID,String HiveID,String Date,String Hive_Stat,String Frames,String Population, String Location,String Notes){
        this.setID(ID);
        this.setHiveID(HiveID);
        this.setDate(Date);
        this.setHive_Stat(Hive_Stat);
        this.setFrames(Frames);
        this.setPopulation(Population);
        this.setLocation(Location);
        this.setNotes(Notes);
    }
    public Hives(){

    }
    public Hives(String ID){

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
