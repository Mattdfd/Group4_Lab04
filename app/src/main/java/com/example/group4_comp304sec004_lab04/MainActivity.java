package com.example.group4_comp304sec004_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Hey can you see this?
    //I can see this
    //Awesome it worked!
    Button login, addNurse;
    private NurseViewModel nurseViewModel;
    private TextView nurseList;
    private EditText firstName, lastName, department, password;
    Nurse nurse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.loginButton);
        addNurse = (Button) findViewById(R.id.AddNurseButton);
        nurseList = (TextView) findViewById(R.id.nurseList);

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Login.class);
                startActivity(intent);
            }
        });

            nurseViewModel.getAllNurses().observe(this, new Observer<List<Nurse>>() {
                @Override
                public void onChanged(@Nullable List<Nurse> nurses) {
                    String output = "***********List of available nurses**********\n";
                    for (Nurse nurse : nurses) {
                        output += "Id: "+nurse.getNurseId()+" | Name: "+nurse.getFirstname() + " " + nurse.getLastname() +" | Dep: "
                                +nurse.getDepartment()+" | Pass: "+nurse.getPassword()+"\n";
                    }
                    nurseList.setText(output);
                }
            });


        addNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = findViewById(R.id.firstName);
                lastName = findViewById(R.id.lastName);
                department = findViewById(R.id.department);
                password = findViewById(R.id.password);

                if (firstName.getText().toString().isEmpty()
                    || lastName.getText().toString().isEmpty()
                || department.getText().toString().isEmpty()
                || password.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    nurse = new Nurse(firstName.getText().toString(), lastName.getText().toString(), department.getText().toString(), password.getText().toString());
                    nurseViewModel.insert(nurse);
                }
            }
        });


    }


}