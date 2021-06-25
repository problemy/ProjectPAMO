package com.example.smartarrium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ControllerConnector extends AppCompatActivity {
    EditText ipInput, apiKeyInput, nameInput;
    TextView ipLabel, apiKeyLabel, nameLabel;
    Button submit, controllerForm;
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
        controllerForm = findViewById(R.id.controllerForm);

    }
    /*
    *Method is changing buttons visibility after clickin them
     */
    public void showForm(View view){
        ipLabel.setVisibility(View.VISIBLE);
        ipInput.setVisibility(View.VISIBLE);
        apiKeyLabel.setVisibility(View.VISIBLE);
        apiKeyInput.setVisibility(View.VISIBLE);
        nameLabel.setVisibility(View.VISIBLE);
        nameInput.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
        controllerForm.setVisibility(View.GONE);
    };
    /*
    *Method starts new activity -  ScannerQR
     */
    public void goToQrCodeScanner(View view){

            Intent scannerIntent = new Intent(ControllerConnector.this, ScannerQR.class);
            startActivity(scannerIntent);
    }
    /*
    * Method submits form, if form is null it make Toast with proper information
    * need validation, crashes when ip/name/apikey is not correct
     */
    public void submitControllerForm(View view) {
        //validation to do
        Controller controller = new Controller(apiKeyInput.getText().toString(),
                nameInput.getText().toString(),ipInput.getText().toString());
        if (controller != null) {
            Intent homeIntent = new Intent(ControllerConnector.this, MainActivity.class);
            startActivity(homeIntent);
        } else {
            Toast.makeText(getApplicationContext(), "Wprowadzone dane są nieprawidłowe, użyj skanera QR", Toast.LENGTH_SHORT).show();
        }
    }

}