package com.example.sqlitedbex;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText  editTextName, editTextPassword, editTextEmail, editTextUserId;
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxNewsletter;
    private Button buttonSave, buttonUpdate, buttonDelete, buttonClear, buttonView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Initialize all UI Components
        editTextUserId = findViewById(R.id.editTextUserId);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkBoxNewsletter = findViewById(R.id.checkBoxNewsletter);
        buttonSave = findViewById(R.id.btnSave);
        buttonUpdate = findViewById(R.id.btnUpdate);
        buttonClear = findViewById(R.id.btnClear);
        buttonView = findViewById(R.id.btnViewAll);
        buttonDelete = findViewById(R.id.btnDelete);

        //Initialize database helper
        db = new DatabaseHelper(getApplicationContext());

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUserData();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearUserData();
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewUserData();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void saveUserData()
    {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        String newsletter = checkBoxNewsletter.isChecked() ? "Yes" : "No";

        boolean isInserted = db.insertData(name,email,password,gender,newsletter);

        if(isInserted)
        {
            Toast.makeText(getApplicationContext(),"Data entered successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }

    }
    private void updateUserData()
    {
        String userid = editTextUserId.getText().toString();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        String newsletter = checkBoxNewsletter.isChecked() ? "Yes" : "No";

        boolean isUpdated = db.updateData(userid,name,email,password,gender,newsletter);
        if(isUpdated)
        {
            Toast.makeText(getApplicationContext(),"Data modified successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }

    }
    private void deleteUserData()
    {
        String userid = editTextUserId.getText().toString();
        Integer deletedRows = db.deleteData(userid);
        if(deletedRows>0)
        {
            Toast.makeText(getApplicationContext(),"User Deleted successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }

    }
    private void clearUserData()
    {
        editTextUserId.setText("");
        editTextName.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        radioGroupGender.clearCheck();
        checkBoxNewsletter.setChecked(false);
    }
    private void viewUserData()
    {
        Cursor res = db.getAllData();
        if(res.getCount() == 0)
        {
            showData("Error","No data found");
        }
        StringBuilder builder = new StringBuilder();
        while(res.moveToNext())
        {
            builder.append("ID: ").append(res.getString(0)).append("\n");
            builder.append("Name : ").append(res.getString(1)).append("\n");
            builder.append("Email : ").append(res.getString(2)).append("\n");
            builder.append("Gender : ").append(res.getString(4)).append("\n");
            builder.append("Newsletter : ").append(res.getString(5)).append("\n\n");

            //show data in alert dialog
            showData("User  Data",builder.toString());
        }
    }
    private  void showData(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
