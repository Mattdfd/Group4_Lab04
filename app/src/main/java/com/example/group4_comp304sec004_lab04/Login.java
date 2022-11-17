package com.example.group4_comp304sec004_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class
Login extends AppCompatActivity {
    EditText nurseID, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login Page");

        nurseID = (EditText) findViewById(R.id.enterNurseID);
        password = (EditText) findViewById(R.id.enterPassword);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PatientActivity.class);
                startActivity(intent);
            }
        });

    }
}