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
    private boolean Lamp;
    private int Humidity;
    private double Temperature;
    private double Pressure;
    private boolean Motion;
    private boolean Heating;
    private int SunriseHour;
    private int SunriseMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        Button lampBtn = (Button) findViewById(R.id.lampButton);
        Button scheduleBtn = (Button) findViewById(R.id.scheduleButton);
        getSensors();


        lampBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSensors();
                updateHeating();
            }
        });
        scheduleBtn.setOnClickListener(view -> {
            Intent scheduleIntent = new Intent(MainActivity.this, Scheduler.class);
            startActivity(scheduleIntent);

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
                    Pressure = sensor.getPressure();
                    Humidity = sensor.getHumidity();
                    Temperature = sensor.getTemperature();
                    Motion = sensor.isMotion();
                    Heating = sensor.isHeating();
                    Lamp = sensor.isLamp();
                    SunriseHour = sensor.getSunriseHour();
                    SunriseMinute = sensor.getSunriseMinute();

                            String content ="";
                            content += "Pressure: " + sensor.getPressure()+ "\n\n";
                            content += "Humidity: " + sensor.getHumidity()+ "\n\n";
                            content += "temperature: " + sensor.getTemperature()+ "\n\n";
                            content += "motion: " + sensor.isMotion()+ "\n\n";
                            content += "heating: " + sensor.isHeating()+ "\n\n";
                            content += "lamp: " + sensor.isLamp()+ "\n\n";
                            content += "sunriseHour: " + sensor.getSunriseHour()+ "\n\n";
                            content += "sunriseMinute: " + sensor.getSunriseMinute()+ "\n\n";
                            textViewResult.setText(content);

                }

                @Override
                public void onFailure(Call<Sensor> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    private void updateSensors()  {

        Sensor sensor = new Sensor(Lamp,Heating,Motion,Pressure,Temperature,Humidity,SunriseHour,SunriseMinute);

        Lamp = sensor.setLamp(true);
        Motion = sensor.setMotion(true);

        Call<Sensor> call = RetrofitClient.getInstance().getMyApi().updateSensors(sensor);
        call.enqueue(new Callback<Sensor>() {
            @Override
            public void onResponse(Call<Sensor> call, Response<Sensor> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code:" + response.code());
                    return;
                }

//
//                String content ="";
//                content += "Pressure: " + sensor.getPressure()+ "\n\n";
//                content += "Humidity: " + sensor.getHumidity()+ "\n\n";
//                content += "temperature: " + sensor.getTemperature()+ "\n\n";
//                content += "motion: " + sensor.isMotion()+ "\n\n";
//                content += "heating: " + sensor.isHeating()+ "\n\n";
//                content += "lamp: " + sensor.isLamp()+ "\n\n";
//                content += "sunriseHour: " + sensor.getSunriseHour()+ "\n\n";
//                content += "sunriseMinute: " + sensor.getSunriseMinute()+ "\n\n";
//                textViewResult.setText(content);

            }

            @Override
            public void onFailure(Call<Sensor> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateHeating() {
        Sensor sensor = new Sensor(Lamp,Heating,Motion,Pressure,Temperature,Humidity,SunriseHour,SunriseMinute);


       Heating = sensor.setHeating(true);



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