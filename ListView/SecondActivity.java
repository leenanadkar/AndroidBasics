package com.example.listviewadapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_activity);

        //UI Component
        ListView listView = (ListView) findViewById(R.id.listViewUI);

        //Data source - Array

        String[] states = {"Maharashtra","Gujrat","HP","MP","UP"};

        //Bind -      //Array Adapter
        ArrayAdapter<String> statesAdapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,states);

       // setContentView(listView);

        //Bind listview to Adapter

       listView.setAdapter(statesAdapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position==0)
               {
                   Toast.makeText(SecondActivity.this, "Maharashtra is Clicked", Toast.LENGTH_LONG).show();
                   startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
               } else if (position == 1) {
                   Toast.makeText(SecondActivity.this, "Gujrat is Clicked", Toast.LENGTH_LONG).show();
               }
           }
       });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
