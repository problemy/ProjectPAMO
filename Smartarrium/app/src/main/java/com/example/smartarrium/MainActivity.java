package com.example.smartarrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import okhttp3.MediaType;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

   private TextView textViewResult;

    String API_KEY = "Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImEyN2ZhZTQ2LTE3YzEtNDhhYS1hNjVlLTA0ZDBiZTA5MjhhYSJ9.eyJjbGllbnRfaWQiOiJsb2NhbC10b2tlbiIsInJvbGUiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZSI6Ii90aGluZ3M6cmVhZHdyaXRlIiwiaWF0IjoxNjE4Njc0MzU4LCJpc3MiOiJodHRwczovL3NtYXJ0YXJyaXVtLndlYnRoaW5ncy5pbyJ9.iQP5YrGfuE2I84faNmNgeRg3i8KWM7psansnWb2YqumXgcUkQnORP9T_oy5_StIDkPiCUPM3GTtH7tRMKhGSrQ";
    private boolean lamp;
    private int humidity;
    private double temperature;
    private double pressure;
    private boolean motion;
    private boolean heating;
    private int sunriseHour;
    private int sunriseMinute;
    private int nightfallHour;
    private int sunsetMinute;
    private double dayTemperature;
    private double nightTemperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        Button lampBtn = findViewById(R.id.lampButton);
        Button scheduleBtn =  findViewById(R.id.scheduleButton);
        Button refreshBtn = findViewById(R.id.refreshButton);
        getSensors();


        lampBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLamp();
                updateHeating();
            }
        });
        scheduleBtn.setOnClickListener(view -> {
            Intent scheduleIntent = new Intent(MainActivity.this, Scheduler.class);
            startActivity(scheduleIntent);

        });
        refreshBtn.setOnClickListener(view -> {
            getSensors();
        });
    }


    //public void switchLamp(View view){ updateSensors(); }





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
                    lamp = sensor.isLamp();
                    sunriseHour = sensor.getSunriseHour();
                    sunriseMinute = sensor.getSunriseMinute();
                    nightfallHour = sensor.getNightfallHour();
                    sunsetMinute = sensor.getNightfallMinute();
                    dayTemperature = sensor.getTargetDayTemperature();
                    nightTemperature = sensor.getTargetNightTemperature();
                 //   Id = sensor.getId();

                            String content ="\n\nWarunki panujące w terrarium:";
                            content += "\n\nCiśnienie: " + sensor.getPressure()+ "\n\n";
                            content += "Wilgotność: " + sensor.getHumidity()+ "%\n\n";
                            content += "Temperatura: " + sensor.getTemperature()+ "\n\n";
                            content += "Czy wykryto ruch: " + sensor.isMotion()+ "\n\n";
                            content += "Ocieplenie: " + sensor.isHeating()+ "\n\n";
                            content += "Oświetlenie: " + sensor.isLamp()+ "\n\n";
                            content += "Godzina rozpoczęcia dnia: " + sensor.getSunriseHour()+":"+ sensor.getSunriseMinute()+ "\n\n";
                            content += "Rozpoczęcie nocy : " + sensor.getNightfallHour()+":"+sensor.getNightfallMinute()+ "\n\n";
                            content += "Temperatura w dzień: " + sensor.getTargetDayTemperature()+ "\n\n";
                            content += "Temperatura w nocy: " + sensor.getTargetNightTemperature()+ "\n\n";
                            textViewResult.setText(content);

                }

                @Override
                public void onFailure(Call<Sensor> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    private void updateLamp()  {

        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,sunriseMinute,nightfallHour,sunsetMinute,dayTemperature,nightTemperature);

        lamp = sensor.setLamp(true);



        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().updateLamp(sensor);
        call.enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code:" + response.code());
                    return;
                }

            getSensors();
            }

            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateHeating() {
        Sensor sensor = new Sensor(lamp,heating,motion,pressure,temperature,humidity,sunriseHour,sunriseMinute,nightfallHour,sunsetMinute,dayTemperature,nightTemperature);


        heating = sensor.setHeating(true);



        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().updateHeating(sensor);
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