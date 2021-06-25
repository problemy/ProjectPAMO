package com.example.smartarrium;

import android.widget.EditText;
import android.widget.Toast;

public class ScheduleFormValidator {

    private int SunriseHour,SunriseMinute,DayTemperature, SunsetHour,SunsetMinute,NightTemperature;

    static String  validate(int SunriseHour, int SunriseMinute, int DayTemperature,
                            int SunsetHour, int SunsetMinute, int NightTemperature) {
        String content = "";


        if (SunriseHour < 0 || SunriseHour > 24) {
            content += "Godzina dnia musi być z zakresu 0-23 \n";
        }
        else if (SunriseMinute < 0 || SunriseMinute > 59) {
            content += "Minuta dnia musi być z zakresu 0-59 \n";
        }
        else if (SunsetHour < 0 || SunsetHour > 24) {
            content += "Godzina nocy musi być z zakresu 0-23 \n";
        }
        else if (SunsetMinute < 0 || SunsetMinute > 59) {
            content += "Minuta nocy musi być z zakresu 0-59 \n";
        }
        else if (DayTemperature < 0 || DayTemperature > 51) {
            content += "Temperatura dnia musi być z zakresu 0-50 \n";
        }
        else if (NightTemperature < 0 || NightTemperature > 51) {
            content += "Temperatura nocy musi być z zakresu 0-50 \n";
        } else {
            content += "Sukces!";
        }

        //formResult.setText(content);
        //}


        return content;

    }
}