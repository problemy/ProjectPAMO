package com.example.smartarrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ControllerConnector extends AppCompatActivity {
    EditText ipInput, apiKeyInput, nameInput;
    TextView ipLabel, apiKeyLabel, nameLabel;
    Button submit, scanQR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller_connector);
        ipLabel = findViewById(R.id.ipLabel);
        ipInput = findViewById(R.id.ipInput);
        apiKeyLabel = findViewById(R.id.apiLabel);
        apiKeyInput = findViewById(R.id.apiInput);
        nameLabel = findViewById(R.id.nameLabel);
        nameInput = findViewById(R.id.nameInput);
        nameLabel = findViewById(R.id.nameLabel);
        nameInput = findViewById(R.id.nameInput);

        submit = findViewById(R.id.submitForm);
        scanQR = findViewById(R.id.scanQrCode);
    }

    public void showForm(View view){
        ipLabel.setVisibility(View.VISIBLE);
        ipInput.setVisibility(View.VISIBLE);
        apiKeyLabel.setVisibility(View.VISIBLE);
        apiKeyInput.setVisibility(View.VISIBLE);
        nameLabel.setVisibility(View.VISIBLE);
        nameInput.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
        scanQR.setVisibility(View.GONE);
    };

    public void goToQrCodeScanner(View view){

            Intent scannerIntent = new Intent(ControllerConnector.this, ScannerQR.class);
            startActivity(scannerIntent);
    }

}