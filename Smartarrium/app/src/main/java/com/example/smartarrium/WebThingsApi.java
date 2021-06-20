package com.example.smartarrium;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebThingsApi {
    String BASE_URL = "http://192.168.1.42:8080/things/http---sterownik.local-things-002/";


    @Headers({"accept: application/json",
            "authorization:Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImEyN2ZhZTQ2LTE3YzEtNDhhYS1hNjVlLTA0ZDBiZTA5MjhhYSJ9.eyJjbGllbnRfaWQiOiJsb2NhbC10b2tlbiIsInJvbGUiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZSI6Ii90aGluZ3M6cmVhZHdyaXRlIiwiaWF0IjoxNjE4Njc0MzU4LCJpc3MiOiJodHRwczovL3NtYXJ0YXJyaXVtLndlYnRoaW5ncy5pbyJ9.iQP5YrGfuE2I84faNmNgeRg3i8KWM7psansnWb2YqumXgcUkQnORP9T_oy5_StIDkPiCUPM3GTtH7tRMKhGSrQ"})
    @GET("properties")
    Call<Sensor> getSensors();


    @Headers({"accept: application/json","content-type: application/json",
            "authorization:Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImEyN2ZhZTQ2LTE3YzEtNDhhYS1hNjVlLTA0ZDBiZTA5MjhhYSJ9.eyJjbGllbnRfaWQiOiJsb2NhbC10b2tlbiIsInJvbGUiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZSI6Ii90aGluZ3M6cmVhZHdyaXRlIiwiaWF0IjoxNjE4Njc0MzU4LCJpc3MiOiJodHRwczovL3NtYXJ0YXJyaXVtLndlYnRoaW5ncy5pbyJ9.iQP5YrGfuE2I84faNmNgeRg3i8KWM7psansnWb2YqumXgcUkQnORP9T_oy5_StIDkPiCUPM3GTtH7tRMKhGSrQ"})
    @PUT("properties/lamp")
    Call<Sensor> updateSensors(@Body Sensor sensor);
    @Headers({"accept: application/json","content-type: application/json",
            "authorization:Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImEyN2ZhZTQ2LTE3YzEtNDhhYS1hNjVlLTA0ZDBiZTA5MjhhYSJ9.eyJjbGllbnRfaWQiOiJsb2NhbC10b2tlbiIsInJvbGUiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZSI6Ii90aGluZ3M6cmVhZHdyaXRlIiwiaWF0IjoxNjE4Njc0MzU4LCJpc3MiOiJodHRwczovL3NtYXJ0YXJyaXVtLndlYnRoaW5ncy5pbyJ9.iQP5YrGfuE2I84faNmNgeRg3i8KWM7psansnWb2YqumXgcUkQnORP9T_oy5_StIDkPiCUPM3GTtH7tRMKhGSrQ"})
    @PUT("properties/heating")
    Call<Sensor> updateHeating(@Body Sensor sensor);

}
