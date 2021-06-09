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

    public Sensor(boolean lamp,boolean heating,boolean motion,double pressure,double  temperature,int humidity,int sunriseHour, int sunriseMinute){
        this.lamp = lamp;
        this.heating = heating;
        this.motion = motion;
        this.pressure = pressure;
        this.temperature = temperature;
        this. humidity = humidity;
        this.sunriseHour = sunriseHour;
        this.sunriseMinute = sunriseMinute;


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
    public double setTemperature() {return temperature;}
    public boolean isMotion() {
        return motion;
    }
    public boolean setMotion(boolean motion) {return this.motion = motion;}

    public double getPressure() {
        return pressure;
    }
    public double setPressure() {return pressure;}


    @SerializedName("humidity")
    public int getHumidity(){
        return humidity;
    }
    public int setHumidity() {return humidity;}

    public int getSunriseHour(){
        return sunriseHour;
    }
    public int setSunriseHour() {return sunriseHour;}

    public int getSunriseMinute(){
        return sunriseMinute;
    }
    public int setSunriseMinute() {return sunriseMinute;}


    
}
