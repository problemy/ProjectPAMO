package com.example.smartarrium;

import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.BreakIterator;

import static org.junit.Assert.assertEquals;

public class ScheduleFormValidatorTest {

    int sunriseHour, sunriseMinute,
     nightfallHour, nightfallMinute,
     DayTemperature, NightTemperature;


    @Before
    public void setUp() {

        sunriseHour = 0;
        sunriseMinute = 0;
        nightfallHour = 0;
        nightfallMinute = 0;
        DayTemperature = 0;
        NightTemperature = 0;

    }

    @After
    public void tearDown() {
//        sunriseHour = null;
//        sunriseMinute = null;
//        nightfallHour = null;
//        nightfallMinute= null;
//        DayTemperature = null;
//        NightTemperature = null;
    }


    @Test
    public void validateSunriseHourTest() {
        sunriseHour = 12;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Sukces!", content );
    }
    @Test
    public void validateSunriseHourTest1() {
        sunriseHour = 25;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Godzina dnia musi być z zakresu 0-23 \n", content );
    }
    @Test
    public void validateSunriseHourTest2() {
        sunriseHour = -1;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Godzina dnia musi być z zakresu 0-23 \n", content );
    }
    @Test
    public void validateSunriseMinuteTest() {
        sunriseMinute = 0;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Sukces!", content );
    }
    @Test
    public void validateSunriseMinuteTest1() {
        sunriseMinute = 60;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Minuta dnia musi być z zakresu 0-59 \n", content );
    }
    @Test
    public void validateSunriseMinuteTest2() {
        sunriseMinute = -1;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Minuta dnia musi być z zakresu 0-59 \n", content );
    }
    @Test
    public void validateNightfallHourTest() {
        nightfallHour = 0;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Sukces!", content );
    }
    @Test
    public void validateNightfallHourTest1() {
        nightfallHour = 60;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Godzina nocy musi być z zakresu 0-23 \n", content );
    }
    @Test
    public void validateNightfallHourTest2() {
        nightfallHour = -1;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Godzina nocy musi być z zakresu 0-23 \n", content );
    }
    @Test
    public void validateDayTemperatureTest() {
        DayTemperature = 35;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Sukces!", content );
    }
    @Test
    public void validateDayTemperatureTest1() {
        DayTemperature = 66;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Temperatura dnia musi być z zakresu 0-50 \n", content );
    }
    @Test
    public void validateDayTemperatureTest2() {
        DayTemperature = -8;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Temperatura dnia musi być z zakresu 0-50 \n", content );
    }
    @Test
    public void validateNightTemperatureTest() {
        NightTemperature = 35;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Sukces!", content );
    }
    @Test
    public void validateNightTemperatureTest1() {
        NightTemperature = 66;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Temperatura nocy musi być z zakresu 0-50 \n", content );
    }
    @Test
    public void validateNightTemperatureTest2() {
        NightTemperature = -8;
        String content = ScheduleFormValidator.validate(sunriseHour, sunriseMinute, DayTemperature, nightfallHour,
                nightfallHour, NightTemperature);
        assertEquals("Temperatura nocy musi być z zakresu 0-50 \n", content );
    }

}