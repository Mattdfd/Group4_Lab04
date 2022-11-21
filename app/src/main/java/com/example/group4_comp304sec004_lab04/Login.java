package com.example.group4_comp304sec004_lab04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class
Login extends AppCompatActivity {
    SharedPreferences nursePreferences;
    SharedPreferences.Editor editor;
    EditText nurseID, password;
    NurseViewModel nurseViewModel;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login Page");

        //retrieving shraed preferences
        nursePreferences = getApplicationContext().getSharedPreferences("loginPreferences",MODE_PRIVATE);
        editor = nursePreferences.edit();

        //updating edit text boxes
        nurseID = (EditText) findViewById(R.id.enterNurseID);
        password = (EditText) findViewById(R.id.enterPassword);

        nurseID.setText(nursePreferences.getString("ID",""));
        password.setText(nursePreferences.getString("Password",""));

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
        AtomicBoolean found = new AtomicBoolean(false);
        int id = Integer.parseInt(nurseID.getText().toString());
        nurseViewModel.getAllNurses().observe(this, (Observer<List<Nurse>>) nurses -> {
            for (Nurse nurse : nurses)
            {
                if (nurse.getNurseId()==id && password.getText().toString().equals(nurse.getPassword())){
                    found.set(true);
                    editor.putString("ID",nurseID.getText().toString());
                    editor.putString("Password",password.getText().toString());
                    editor.commit();
                    Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), PatientActivity.class);
                    startActivity(intent);
                    break;
                }
            }

            if (!found.get()){
                Toast.makeText(Login.this, "Id or password incorrect", Toast.LENGTH_SHORT).show();
            }

        });


    }
}