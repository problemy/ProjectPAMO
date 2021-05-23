package com.example.smartarrium;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebThingsApi {
    String BASE_URL = "http://192.168.1.67:8080/things/http---sterownik.local-things-002/properties/";


    @Headers({"accept: application/json",
            "authorization:Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImEyN2ZhZTQ2LTE3YzEtNDhhYS1hNjVlLTA0ZDBiZTA5MjhhYSJ9.eyJjbGllbnRfaWQiOiJsb2NhbC10b2tlbiIsInJvbGUiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZSI6Ii90aGluZ3M6cmVhZHdyaXRlIiwiaWF0IjoxNjE4Njc0MzU4LCJpc3MiOiJodHRwczovL3NtYXJ0YXJyaXVtLndlYnRoaW5ncy5pbyJ9.iQP5YrGfuE2I84faNmNgeRg3i8KWM7psansnWb2YqumXgcUkQnORP9T_oy5_StIDkPiCUPM3GTtH7tRMKhGSrQ"})
    @GET("humidity")
    Call<Sensor> getSensors();





}
