package com.example.group4_comp304sec004_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class
Login extends AppCompatActivity {
    EditText nurseID, password;
    NurseViewModel nurseViewModel;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login Page");

        nurseID = (EditText) findViewById(R.id.enterNurseID);
        password = (EditText) findViewById(R.id.enterPassword);
        login = (Button) findViewById(R.id.login);

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNurse();
            }
        });

    }

    public void findNurse(){
        int id = Integer.parseInt(nurseID.getText().toString());
        nurseViewModel.getAllNurses().observe(this, (Observer<List<Nurse>>) nurses -> {
            for (Nurse nurse : nurses)
            {
                if (nurse.getNurseId()==id && password.getText().toString().equals(nurse.getPassword())){
                    Intent intent = new Intent(getBaseContext(), PatientActivity.class);
                    Toast.makeText(this, "Nurse Id Found", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else {
                    Toast.makeText(Login.this, "Id or password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}