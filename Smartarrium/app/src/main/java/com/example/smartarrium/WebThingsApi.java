package com.example.smartarrium;



import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

public interface WebThingsApi {

    String accept = "accept: application/json";
    String authorization = "authorization:Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImEyN2ZhZTQ2LTE3YzEtNDhhYS1hNjVlLTA0ZDBiZTA5MjhhYSJ9.eyJjbGllbnRfaWQiOiJsb2NhbC10b2tlbiIsInJvbGUiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZSI6Ii90aGluZ3M6cmVhZHdyaXRlIiwiaWF0IjoxNjE4Njc0MzU4LCJpc3MiOiJodHRwczovL3NtYXJ0YXJyaXVtLndlYnRoaW5ncy5pbyJ9.iQP5YrGfuE2I84faNmNgeRg3i8KWM7psansnWb2YqumXgcUkQnORP9T_oy5_StIDkPiCUPM3GTtH7tRMKhGSrQ";
    String content_type = "content-type: application/json";

    @Headers({accept, authorization
            })
    @GET("properties")
    Call<Sensor> getSensors();



    @Headers({accept,content_type,authorization})
    @PUT("properties/lamp")
    Call<Sensor> updateLamp(@Body Sensor sensor);
    @Headers({accept,content_type,authorization})
    @PUT("properties/heating")
    Call<Sensor> updateHeating(@Body Sensor sensor);

    @Headers({accept,content_type,authorization})
    @PUT("properties/scheduleModeOn")
    Call<Sensor> updateScheduleMode(@Body Sensor sensor);


    @Headers({accept,content_type,authorization})
    @PUT("properties/sunriseMinute")
    Call<Sensor> setSunriseMinute(@Body Sensor sensor);

    @Headers({accept,content_type})
    @PUT("properties/sunriseHour")
    Call<Sensor> setSunriseHour(@Body Sensor sensor);

    @Headers({accept,content_type,authorization})
    @PUT("properties/nightfallHour")
    Call<Sensor> setNightfallHour(@Body Sensor sensor);

    @Headers({accept,content_type,authorization})
    @PUT("properties/targetDayTemperature")
    Call<Sensor> setTargetDayTemperature(@Body Sensor sensor);

    @Headers({accept,content_type,authorization})
    @PUT("properties/targetNightTemperature")
    Call<Sensor> setTargetNightTemperature(@Body Sensor sensor);

    @Headers({accept,content_type,authorization})
    @PUT("properties/nightfallMinute")
    Call<Sensor> setNightfallMinute(@Body Sensor sensor);
   @Headers({accept,content_type,authorization})
    @PUT("properties/lamp")
    Call<Sensor> setLamp(@Body Sensor sensor);
}
