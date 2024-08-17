package com.example.registrationform;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editUsername, editEmailId;
    RadioGroup radioGroup;
    RadioButton radioMorning, radioEvening;
    CheckBox checkBoxLunch, checkBoxNetworking;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsername = findViewById(R.id.editUsername);
        editEmailId = findViewById(R.id.editEmailId);
        radioGroup = findViewById(R.id.RGSession);
        radioMorning = findViewById(R.id.radioMorning);
        radioEvening = findViewById(R.id.radioEvening);
        checkBoxLunch = findViewById(R.id.checkBoxLunch);
        checkBoxNetworking = findViewById(R.id.checkBoxNetworking);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capture user input
                String username = editUsername.getText().toString();
                String emailId = editEmailId.getText().toString();
                String session = "";
                if (radioMorning.isChecked()) {
                    session = "Morning";
                } else if (radioEvening.isChecked()) {
                    session = "Evening";
                }
                boolean hasLunch = checkBoxLunch.isChecked();
                boolean hasNetworking = checkBoxNetworking.isChecked();

                // Create an Intent to start SecondActivity
                Intent intent = new Intent(MainActivity.this, com.example.registrationform.SecondActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("emailId", emailId);
                intent.putExtra("session", session);
                intent.putExtra("hasLunch", hasLunch);
                intent.putExtra("hasNetworking", hasNetworking);

                // Start SecondActivity
                startActivity(intent);
            }
        });
    }
}
