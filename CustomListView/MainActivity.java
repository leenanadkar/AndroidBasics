package com.example.customadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String vegetableList[] = {"Potato","Tomato","Onion"};
    int vegatableImages[] = {R.drawable.potato,R.drawable.tomato,R.drawable.onion};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),vegetableList,vegatableImages);

        //Set adapter for listView
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Toast.makeText(getApplicationContext(),"Potato",Toast.LENGTH_LONG).show();
                }
                if(position == 1)
                {
                    Toast.makeText(getApplicationContext(),"Tomoto",Toast.LENGTH_LONG).show();
                }
                if (position == 2)
                {
                    Toast.makeText(getApplicationContext(),"Onion",Toast.LENGTH_LONG).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
