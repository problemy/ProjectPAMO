package com.example.smartarrium;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
* Singleton Instance of Retrofit
*
*/
public class RetrofitClient {

    private static RetrofitClient instance = null;
    private static String controllerUrl;
    private WebThingsApi myApi;
    // BASE_URL = "http://192.168.1.42/things/http---sterownik.local-things-002/";

    //Controller controller;
    String BASE_URL = Controller.getControllerUrl();

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(WebThingsApi.class);

    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public WebThingsApi getMyApi() {
        return myApi;
    }



}

