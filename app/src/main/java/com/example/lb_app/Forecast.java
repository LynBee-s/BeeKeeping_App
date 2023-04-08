package com.example.lb_app;
public class Forecast {
    private String Date;
    private String Day;
    private String Temperature;
    private String Conditions;

    public Forecast(String Date, String Day,String Temperature,String Conditions){
        this.setDate(Date);
        this.setDay(Day);
        this.setTemperature(Temperature);
        this.setConditions(Conditions);
    }

    public  Forecast(){}
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getConditions() {
        return Conditions;
    }

    public void setConditions(String conditions) {
        Conditions = conditions;
    }
}
