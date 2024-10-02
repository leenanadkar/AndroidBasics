package com.example.filehandling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.filehandling.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword,editTextUserId;
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxNewsletter;
    private Button btnSave, btnUpdate, btnClear;
    private static final String FILENAME = "users_data.csv";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUserId =findViewById(R.id.editTextUserId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkBoxNewsletter = findViewById(R.id.checkBoxNewsletter);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnClear = findViewById(R.id.btnClear);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });
    }

    // Method to save user data to CSV
    private void saveUserData() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = sha256(editTextPassword.getText().toString());
        String gender = ((RadioButton)findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        String newsletter = checkBoxNewsletter.isChecked() ? "Yes" : "No";

        int userId = generateUserId();

        String csvLine = userId + "," + name + "," + email + "," +password +  "," +gender +  "," + newsletter + "\n";
        try(FileOutputStream fos = openFileOutput(FILENAME,MODE_APPEND))
        {
            //Open File
            //Add Data
            //File exists - append don't write

            fos.write(csvLine.getBytes());


            Toast.makeText(getApplicationContext(),"Data saved successfully",Toast.LENGTH_LONG).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error saving data",Toast.LENGTH_LONG).show();
        }



    }

    // Method to update user data in CSV
    private void updateUserData() {
        String userIdtoUpdate = editTextUserId.getText().toString();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = sha256(editTextPassword.getText().toString());
        String gender = ((RadioButton)findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        String newsletter = checkBoxNewsletter.isChecked() ? "Yes" : "No";

        FileInputStream fis;
        FileOutputStream fos;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            //Step 1: Read existing CSV File
            fis = openFileInput(FILENAME);
            bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while((line = bufferedReader.readLine()) !=null) {
                String[] fields = line.split(",");
                String userId = fields[0];

                //Step 2: Check if the userId matches the one to update
                if (userId.equals(userIdtoUpdate))
                {
                    //Step 2.1: Update user record with new data
                    String updatedLine = userId + "," + name + "," + email + "," +password +  "," +gender +  "," + newsletter + "\n";
                    stringBuilder.append(updatedLine);
                }
                else {
                    //Step 2.2: Keep the orignal reecord
                    stringBuilder.append(line).append("\n");
                }
            }
            //Step 3: Write updated content back to the file
            fos = openFileOutput(FILENAME,MODE_PRIVATE);
            fos.write(stringBuilder.toString().getBytes());
            Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_LONG).show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error updating data!",Toast.LENGTH_LONG).show();
        }
        // finally {
        //      try {
        //         if (fis != null) fis.close();
        //         if (fos != null) fos.close();
//if (bufferedReader != null) bufferedReader.close();
        //   }
        //   catch (IOException e)
        //    {
        //        e.printStackTrace();
        //     }
        // }


    }


    // Clear the form fields
    private void clearForm() {
        editTextName.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        radioGroupGender.clearCheck();
        checkBoxNewsletter.setChecked(false);
    }

    // Method to generate a unique user ID
    private int generateUserId() {
        //Give start user id
        int userId = 1;
        try(FileInputStream fis = openFileInput(FILENAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis)))

        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] fields = line.split(",");
                userId = Integer.parseInt(fields[0]) + 1;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return userId;
    }

    // Method to encrypt the password using SHA-256
    private String sha256(String password) {

        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("sha-256");
            //Password - string
            //getBytes = Converted password into bytes
            //password.getbytes
            //sha 256 encryption - hash array - 01010101010101010

            byte[] hash = messageDigest.digest(password.getBytes()); // hash array
            StringBuilder stringBuilder = new StringBuilder();

            //Iterate over each byte in hash array
            for(byte b:hash)
            {
                //010101101 to hexadecimal - toHexString
                stringBuilder.append(Integer.toHexString(0xFF & b));
            }
            return stringBuilder.toString();
        }

        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
