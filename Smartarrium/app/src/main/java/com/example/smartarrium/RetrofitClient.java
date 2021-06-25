package com.example.smartarrium;

import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private WebThingsApi myApi;
    String BASE_URL = "http://192.168.1.42/things/http---sterownik.local-things-002/";

    //Controller controller;
    //String BASE_URL = controller.getControllerUrl();
    public static void setController(Controller controller){
        controller = controller;
    }
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

