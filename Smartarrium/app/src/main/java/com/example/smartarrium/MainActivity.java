package com.example.smartarrium;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity {

   private TextView textViewResult,operatingMode;

    //String API_KEY = "Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImEyN2ZhZTQ2LTE3YzEtNDhhYS1hNjVlLTA0ZDBiZTA5MjhhYSJ9.eyJjbGllbnRfaWQiOiJsb2NhbC10b2tlbiIsInJvbGUiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZSI6Ii90aGluZ3M6cmVhZHdyaXRlIiwiaWF0IjoxNjE4Njc0MzU4LCJpc3MiOiJodHRwczovL3NtYXJ0YXJyaXVtLndlYnRoaW5ncy5pbyJ9.iQP5YrGfuE2I84faNmNgeRg3i8KWM7psansnWb2YqumXgcUkQnORP9T_oy5_StIDkPiCUPM3GTtH7tRMKhGSrQ";
    private boolean lamp;
    private final String lampPrint = "Wyłączone1";
    private final String motionPrint = "Nie wykryto1";
    private final String heatingPrint = "Wyłączone1";
    private final String scheduleModeOnPrint = "Nie wybrano";
    private int humidity;
    private double temperature;
    private double pressure;
    private boolean motion;
    private boolean heating;
    private int sunriseHour;
    private int sunriseMinute;
    private int nightfallHour;
    private int nightfallMinute;
    private double dayTemperature;
    private double nightTemperature;
    private boolean scheduleModeOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        getSensors();
        Button scheduleBtn =  findViewById(R.id.scheduleButton);
        Button refreshBtn = findViewById(R.id.refreshButton);
        Button manualBtn = findViewById(R.id.manualButton);
        updateScheduleMode();

        scheduleBtn.setOnClickListener(view -> {
            Intent scheduleIntent = new Intent(MainActivity.this, Scheduler.class);
            startActivity(scheduleIntent);


        });
        manualBtn.setOnClickListener(view -> {
            Intent manualIntent = new Intent(MainActivity.this, ManualMode.class);
            startActivity(manualIntent);


        });
        refreshBtn.setOnClickListener(view -> {
            getSensors();
        });
    }

    private void getSensors() {

            Call<Sensor> call = RetrofitClient.getInstance().getMyApi().getSensors();
            call.enqueue(new Callback<Sensor>() {
                @Override
                public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                    if (!response.isSuccessful()){
                        textViewResult.setText("Code:" + response.code());
                        return;
                    }


                    Sensor sensor = response.body();
                    pressure = sensor.getPressure();
                    humidity = sensor.getHumidity();
                    temperature = sensor.getTemperature();
                    motion = sensor.isMotion();
                    heating = sensor.isHeating();
                    lamp =  sensor.isLamp();
                    sunriseHour = sensor.getSunriseHour();
                    sunriseMinute = sensor.getSunriseMinute();
                    nightfallHour = sensor.getNightfallHour();
                    nightfallMinute = sensor.getNightfallMinute();
                    dayTemperature = sensor.getTargetDayTemperature();
                    nightTemperature = sensor.getTargetNightTemperature();
                    scheduleModeOn = sensor.isScheduleModeOn();

                    if(sensor.isLamp()){
                        String lampPrint = "Włączone"; } else {
                        String lampPrint = "Wyłączone";
                    }
                    if(sensor.isHeating()){
                        String heatingPrint = "Włączone"; } else {
                        String heatingPrint = "Wyłączone";
                    }
                    if(sensor.isMotion()){
                        String motionPrint = "Wykryto"; } else {
                        String motionPrint = "Nie wykryto";
                    }
                    if(sensor.isScheduleModeOn()){
                        String scheduleModeOnPrint = "Wykryto"; } else {
                        String scheduleModeOnPrint = "Nie wykryto";
                    }



                            String content ="\n\nWarunki panujące w terrarium:";
                            content += "\nCiśnienie: " + pressure+ "\n";
                            content += "Wilgotność: " + humidity+ "%\n";
                            content += "Temperatura: " + temperature+ "\n";
                            content += "Czy wykryto ruch: " + motionPrint+ "\n";
                            content += "Ocieplenie: " + heatingPrint+ "\n";
                            content += "Oświetlenie: " + lampPrint+ "\n";
                            content += "Godzina rozpoczęcia dnia: " + sunriseHour+":"+ sunriseMinute+ "\n";
                            content += "Rozpoczęcie nocy : " + nightfallHour+":"+nightfallMinute+ "\n";
                            content += "Temperatura w dzień: " + dayTemperature+ "\n";
                            content += "Temperatura w nocy: " + nightfallHour+ "\n";
                            textViewResult.setText(content);

                            String opMode = " Tryb pracy sterownika:";
                        if(sensor.isScheduleModeOn()) {
                            opMode += "\n Tryb harmonogramu\n";
                        } else{
                            opMode += "\n Tryb manualny\n";
                        }
                        operatingMode.setText(opMode);

                }

                @Override
                public void onFailure(Call<Sensor> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    private void updateScheduleMode()  {

        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,
                sunriseMinute,nightfallHour,nightfallMinute,dayTemperature,nightTemperature,
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
                    textViewResult.setText("Code:" + response.code());
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