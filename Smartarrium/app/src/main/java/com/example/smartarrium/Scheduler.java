package com.example.smartarrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Scheduler extends AppCompatActivity {
    EditText SunriseHour,SunriseMinute,DayTemperature, SunsetHour,SunsetMinute,
            NightTemperature;
    Button submitBtn;
    TextView formResult;
    int maxDayTemperature, maxNightTemperature;
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
            int sunriseHour = Integer.parseInt(SunriseHour.getText().toString());
            int sunriseMinute = Integer.parseInt(SunriseMinute.getText().toString());
            int sunsetMinute = Integer.parseInt(SunsetMinute.getText().toString());
            int sunsetHour = Integer.parseInt(SunsetHour.getText().toString());
            int dayTemperature = Integer.parseInt(DayTemperature.getText().toString());
            int nightTemperature = Integer.parseInt(NightTemperature.getText().toString());
            Log.e("achtung", DayTemperature.getText().toString());
            String msg = ScheduleFormValidator.validate(sunriseHour, sunriseMinute,
                    dayTemperature, sunsetHour,sunsetMinute, nightTemperature);

            if(msg.equals("Sukces!")){

                this.setSchedule();
                Toast.makeText(getApplicationContext(),
                        msg,
                        Toast.LENGTH_LONG)
                        .show();
                Intent manualIntent = new Intent(Scheduler.this, MainActivity.class);
                startActivity(manualIntent);

            } else {
                Toast.makeText(getApplicationContext(),
                        msg,
                        Toast.LENGTH_LONG)
                        .show();
            }



        });

    }

    private void setSchedule(){
        this.setSunriseHour();
        this.setSunriseMinute();
        this.setNightfallHour();
        this.setNightfallMinute();
        this.setTargetDayTemperature();
        this.setTargetNightTemperature();
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