package com.example.smartarrium;

import com.google.gson.annotations.SerializedName;

public class Sensor {
    private boolean lamp;
    private boolean heating;
    private boolean motion;
    private double pressure;
    private double  temperature;
    private int humidity;
    private int sunriseHour;
    private int sunriseMinute;
    private int nightfallHour;
    private int nightfallMinute;
    private double  targetDayTemperature;
    private double  targetNightTemperature;
    private boolean scheduleModeOn;

    //private int id;

    public Sensor(boolean lamp,boolean heating,boolean motion,double pressure,double  temperature,
                  int humidity,int sunriseHour, int sunriseMinute, int nightfallHour, int nightfallMinute,
                  double targetDayTemperature, double targetNightTemperature, boolean scheduleModeOn){
        this.lamp = lamp;
        this.heating = heating;
        this.motion = motion;
        this.pressure = pressure;
        this.temperature = temperature;
        this.humidity = humidity;
        this.sunriseHour = sunriseHour;
        this.sunriseMinute = sunriseMinute;
        this.nightfallHour = nightfallHour;
        this.nightfallMinute = nightfallMinute;
        this.targetDayTemperature = targetDayTemperature;
        this.targetNightTemperature = targetNightTemperature;
        this.scheduleModeOn = scheduleModeOn;


    }

    public boolean isLamp() {
        return lamp;
    }
    public boolean setLamp(boolean lamp) {return  this.lamp = lamp;}

    public boolean isHeating() {
        return heating;
    }
    public boolean setHeating(boolean heating) {return this.heating= heating;}
    public double getTemperature() {
        return temperature;
    }
    public double getTargetDayTemperature() {
        return targetDayTemperature;
    }
    public double getTargetNightTemperature() {return targetNightTemperature;}
    public double setTargetDayTemperature(double targetDayTemperature) {return this.targetDayTemperature = targetDayTemperature; }
    public double setTargetNightTemperature(double targetNightTemperature) {return this.targetNightTemperature = targetNightTemperature; }
    public boolean isMotion() {
        return motion;
    }

    public double getPressure() {
        return pressure;
    }


    @SerializedName("humidity")
    public int getHumidity(){
        return humidity;
    }

    public int getSunriseHour(){
        return sunriseHour;
    }
    public int setSunriseHour(int sunriseHour) { return sunriseHour;};

    public int getSunriseMinute(){
        return sunriseMinute;
    }
    public int setSunriseMinute(int sunriseMinute) {return sunriseMinute;}
    public int getNightfallHour(){ return nightfallHour; }
    public int setNightfallHour(int nightfallHour) {return nightfallHour;}

    public int getNightfallMinute(){ return nightfallMinute; }
    public int setNightfallMinute(int nightfallMinute) { return  nightfallMinute; }


    public boolean isScheduleModeOn() {
        return scheduleModeOn;
    }
    public boolean setScheduleModeOn(boolean scheduleModeOn) {return  this.scheduleModeOn = scheduleModeOn;}
}
