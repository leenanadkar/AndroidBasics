package com.example.registrationform;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registrationform.R;

public class SecondActivity extends AppCompatActivity {

    TextView textViewConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewConfirmation = findViewById(R.id.textViewConfirmation);

        // Retrieve data from the Intent
        String username = getIntent().getStringExtra("username");
        String emailId = getIntent().getStringExtra("emailId");
        String session = getIntent().getStringExtra("session");
        boolean hasLunch = getIntent().getBooleanExtra("hasLunch", false);
        boolean hasNetworking = getIntent().getBooleanExtra("hasNetworking", false);

        // Build the confirmation message
        String confirmationMessage = "Username: " + username +
                "\nEmail ID: " + emailId +
                "\nSession: " + session +
                "\nLunch: " + (hasLunch ? "Yes" : "No") +
                "\nNetworking: " + (hasNetworking ? "Yes" : "No");

        textViewConfirmation.setText(confirmationMessage);
    }
}
