package com.example.smartarrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Scheduler extends AppCompatActivity {
    EditText SunriseHour,SunriseMinute,DayTemperature, SunsetHour,SunsetMinute,NightTemperature;
    Button submitBtn;
    TextView formResult;
    private boolean lamp;
    private int humidity;
    private double temperature;
    private double pressure;
    private boolean motion;
    private boolean heating;
    private int sunriseHour;
    private int sunriseMinute;
    private int nightfallHour;
    private int nightfallMinute;
    private double targetDayTemperature;
    private double targetNightTemperature;
    private boolean scheduleModeOn;
    public static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        SunriseHour = findViewById(R.id.SunriseHourEt) ;
        SunriseMinute =  findViewById(R.id.SunriseMinuteEt) ;
        DayTemperature =  findViewById(R.id.DayTemperatureEt) ;
        SunsetHour =  findViewById(R.id.SunsetHourEt) ;
        SunsetMinute = findViewById(R.id.SunsetMinuteEt) ;
        NightTemperature =  findViewById(R.id.NightTemperatureEt) ;
        submitBtn = findViewById(R.id.Submit);
        formResult = findViewById(R.id.formResult);



        submitBtn.setOnClickListener(view -> {
            validate(SunriseHour, SunriseMinute,DayTemperature, SunsetHour,SunsetMinute,NightTemperature);



        });

    }
    private void validate(EditText SunriseHourEt, EditText SunriseMinuteEt, EditText DayTemperatureEt, EditText SunsetHourEt, EditText SunsetMinuteEt, EditText NightTemperatureEt) {
        String content = "";

        if (SunriseHourEt.length() < 1 || SunriseMinuteEt.length() < 1 || SunsetHourEt.length() < 1 ||
                SunsetMinuteEt.length() < 1 || DayTemperatureEt.length() < 1 || NightTemperatureEt.length() < 1) {

            content += "Nie kombinuj, wypełnij wszystkie pola";

        } else {

            int SunriseHour = Integer.parseInt(SunriseHourEt.getText().toString());
            int SunriseMinute = Integer.parseInt(SunriseMinuteEt.getText().toString());
            int SunsetMinute = Integer.parseInt(SunsetMinuteEt.getText().toString());
            int SunsetHour = Integer.parseInt(SunsetHourEt.getText().toString());

            int DayTemperature = Integer.parseInt(DayTemperatureEt.getText().toString());
            int NightTemperature = Integer.parseInt(NightTemperatureEt.getText().toString());

            if (SunriseHour < 0 || SunriseHour > 24) {
                content += "Godzina dnia musi być z zakresu 0-24 \n";
            }
            if (SunriseMinute < 0 || SunriseMinute > 59) {
                content += "Minuta dnia musi być z zakresu 0-59 \n";
            }
            if (SunsetHour < 0 || SunsetHour > 24) {
                content += "Godzina nocy musi być z zakresu 0-24 ";
            }
            if (SunsetMinute < 0 || SunsetMinute > 59) {
                content += "Minuta nocy musi być z zakresu 0-59 ";
            }
            if (DayTemperature < 0 || DayTemperature > 50) {
                content += "Temperatura dnia musi być z zakresu 0-50 ";
            }
            if (NightTemperature < 0 || NightTemperature > 50) {
                content += "Temperatura nocy musi być z zakresu 0-50 ";
            } else {

                content += "Success!";
                this.setSunriseHour();
                this.setSunriseMinute();
                this.setNightfallHour();
                this.setNightfallMinute();
                this.setTargetDayTemperature();
                this.setTargetNightTemperature();
                updateScheduleMode();
                new Handler().postDelayed(() -> {
                    Intent homeIntent = new Intent(Scheduler.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                },SPLASH_TIME_OUT);
            }

            //formResult.setText(content);
        }

            Toast.makeText(getApplicationContext(),
                    content,
                    Toast.LENGTH_LONG)
                    .show();


    }

    private void setTargetNightTemperature() {
        double targetNightTemperature = Double.parseDouble(NightTemperature.getText().toString());
        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                sunriseMinute,nightfallHour,nightfallMinute,targetDayTemperature,targetNightTemperature,scheduleModeOn);

        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().setTargetNightTemperature(sensor);
        call.enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                if (!response.isSuccessful()){
                    formResult.setText("Code:" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTargetDayTemperature() {

        double targetDayTemperature = Double.parseDouble(DayTemperature.getText().toString());
        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                sunriseMinute,nightfallHour,nightfallMinute,targetDayTemperature,targetNightTemperature,scheduleModeOn);

        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().setTargetDayTemperature(sensor);
        call.enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                if (!response.isSuccessful()){
                    formResult.setText("Code:" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setNightfallMinute() {

        int nightfallMinute = Integer.parseInt(SunriseMinute.getText().toString());
        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                sunriseMinute,nightfallHour,nightfallMinute,targetDayTemperature,targetNightTemperature,scheduleModeOn);

        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().setNightfallMinute(sensor);
        call.enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                if (!response.isSuccessful()){
                    formResult.setText("Code:" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setNightfallHour() {

        int nightfallHour = Integer.parseInt(SunsetHour.getText().toString());

        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                sunriseMinute,nightfallHour,nightfallMinute,targetDayTemperature,targetNightTemperature,scheduleModeOn);

        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().setNightfallHour(sensor);
        call.enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                if (!response.isSuccessful()){
                    formResult.setText("Code:" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setSunriseMinute()  {

        int sunriseMinute = Integer.parseInt(SunriseMinute.getText().toString());
            Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                    sunriseMinute,nightfallHour,nightfallMinute,targetDayTemperature,targetNightTemperature,scheduleModeOn);

            Call<Sensor> call = RetrofitClient.getInstance().getMyApi().setSunriseMinute(sensor);
            call.enqueue(new Callback<Sensor>() {
                @Override
                public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                    if (!response.isSuccessful()){
                        formResult.setText("Code:" + response.code());
                        return;
                    }
                }
                @Override
                public void onFailure(Call<Sensor> call, Throwable t) {
                   Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    private void setSunriseHour()  {

        int sunriseHour = Integer.parseInt(SunriseHour.getText().toString());
        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                sunriseMinute,nightfallHour,nightfallMinute,targetDayTemperature,targetNightTemperature,scheduleModeOn);

        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().setSunriseHour(sensor);
        call.enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                if (!response.isSuccessful()){
                    formResult.setText("Code:" + response.code());
                    return;
                }
            }
            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateScheduleMode()  {
        boolean scheduleModeOn = true;
        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                sunriseMinute,nightfallHour,nightfallMinute,targetDayTemperature,targetNightTemperature,
                scheduleModeOn);
        if(sensor.isScheduleModeOn()) {
            sensor.setScheduleModeOn(false);
        } else{
            sensor.setScheduleModeOn(true);
        }


        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().updateScheduleMode(sensor);
        call.enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                if (!response.isSuccessful()){
                    formResult.setText("Code:" + response.code());
                    return;
                }

            }

            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}