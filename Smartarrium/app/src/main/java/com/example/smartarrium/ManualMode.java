package com.example.smartarrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManualMode extends AppCompatActivity {
    RadioGroup radioLamp;
    private boolean lamp;
    private boolean rLamp;
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
    EditText temperatureEt;
    Button submitBtn;
    TextView formResult;
    public static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_mode);
        submitBtn = findViewById(R.id.Submit);
        temperatureEt = findViewById(R.id.TemperatureEt);
        formResult = findViewById(R.id.formResult);
        RadioGroup rg = (RadioGroup) findViewById(R.id.RadioLamp);




        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.lampOff:
                        rLamp = false;
                        break;
                    case R.id.lampOn:
                        rLamp = true;
                        break;
                }
            }
        });
        submitBtn.setOnClickListener(view -> {
            validate(temperatureEt);



        });
    }

    private void validate(EditText temperatureEt) {
        String content = "";

        if (temperatureEt.length() < 1) {

            content += "Nie kombinuj, wypełnij wszystkie pola";

        } else {

            int temperature = Integer.parseInt(temperatureEt.getText().toString());


            if (temperature < 0 || temperature > 50) {
                content += "Temperatura nocy musi być z zakresu 0-50 ";
            } else {

                content += "Success!";
                setTargetNightTemperature();
                setTargetDayTemperature();
                setLamp();
                updateScheduleMode();
                new Handler().postDelayed(() -> {
                    Intent homeIntent = new Intent(ManualMode.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                },SPLASH_TIME_OUT);

            }


        }

        Toast.makeText(getApplicationContext(),
                content,
                Toast.LENGTH_LONG)
                .show();


    }




    private void setTargetDayTemperature() {

        double targetDayTemperature = Double.parseDouble(temperatureEt.getText().toString());
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
    private void setTargetNightTemperature() {
        double targetNightTemperature = Double.parseDouble(temperatureEt.getText().toString());
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
    private void setLamp() {
            boolean lamp = rLamp;
            Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                    sunriseMinute,nightfallHour,nightfallMinute,targetDayTemperature,targetNightTemperature,scheduleModeOn);

            Call<Sensor> call = RetrofitClient.getInstance().getMyApi().setLamp(sensor);
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
        boolean scheduleModeOn = false;
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