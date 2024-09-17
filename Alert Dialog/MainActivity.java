package com.example.alertdialogdemoyt;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnAlert;
    private TextView txtResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnAlert = (Button) findViewById(R.id.buttonAlertDemo);
        txtResult = (TextView) findViewById(R.id.textViewResult);

        //Step 1: SetOnClickListener for Button
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 2: Create an alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //SetTitle
                builder.setTitle("Confirm Order");
                //SetMessage
                builder.setMessage("Are you sure you want to continue with your order?");

                //Step 3: Setup Confirm button
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtResult.setText("Order Confirmed");
                    }
                });

                //Step 4: Setup Cancel button
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtResult.setText("Order Cancelled");
                        dialog.dismiss();
                    }
                });

                //Step 5: Setup Neutral button
                builder.setNeutralButton("Choose Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtResult.setText("Please choose another meal");
                    }
                });

                //Step 6: Show the Alert Dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
