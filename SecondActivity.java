package com.example.datepicker2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    TextView textViewSelShowDate;
    DatePickerDialog datePickerDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_activity);

        textViewSelShowDate = (TextView) findViewById(R.id.textViewSelectAndShowDate);
        textViewSelShowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int cal_year = c.get(Calendar.YEAR);
                int cal_month = c.get(Calendar.MONTH);
                int cal_day = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(SecondActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String dateValue = dayOfMonth +"/"+ month + "/" + year;
                        textViewSelShowDate.setText(dateValue);
                    }
                },cal_year,cal_month,cal_day);
                datePickerDialog.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
