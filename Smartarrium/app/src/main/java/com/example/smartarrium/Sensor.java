package com.example.smartarrium;

import com.google.gson.annotations.SerializedName;

public class Sensor {
    private boolean lamp;
    private boolean heating;
    private boolean motion;
    private double pressure;
    private double  temperature;
    private int humidity;

    public Sensor(boolean lamp,boolean heating,boolean motion,double pressure,double  temperature,int humidity){
        this.lamp = lamp;
        this.heating = heating;
        this.motion = motion;
        this.pressure = pressure;
        this.temperature = temperature;
        this. humidity = humidity;


    }
    public boolean isLamp() {
        return lamp;
    }
    public boolean setLamp(boolean b) {return  lamp;}

    public boolean isHeating() {
        return heating;
    }
    public boolean setHeating() {return lamp;}
    public double getTemperature() {
        return temperature;
    }
    public double setTemperature() {return temperature;}
    public boolean isMotion() {
        return motion;
    }
    public boolean setMotion() {return motion;}

    public double getPressure() {
        return pressure;
    }
    public double setPressure() {return pressure;}


    @SerializedName("humidity")
    public int getHumidity(){
        return humidity;
    }
    
}
