package com.example.smartarrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.smartarrium.MainActivity;

public class WelcomeScreen extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 2000;

    /**
     *
     *
     * @see
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                if(Controller.getId()>0) {
                    Intent homeIntent = new Intent(WelcomeScreen.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                } else {
                    Intent homeIntent = new Intent(WelcomeScreen.this, ControllerConnector.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
    }
}