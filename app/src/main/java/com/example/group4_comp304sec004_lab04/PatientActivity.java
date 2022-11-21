package com.example.group4_comp304sec004_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class PatientActivity extends AppCompatActivity {
    private PatientViewModel patientViewModel;
    private EditText firstName;
    private EditText lastName;
    private EditText department;
    private EditText nurseID;
    private EditText room;
    Button viewPatients, enterTest, updatePatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        setTitle("Patient Database");

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        viewPatients = findViewById(R.id.viewPatient);
        enterTest = findViewById(R.id.enterTest);
        updatePatient = findViewById(R.id.updatePatientPI);

        enterTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TestActivity.class);
                startActivity(intent);
            }
        });

        updatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), UpdatePatientInfo.class);
                startActivity(intent);
            }
        });

        viewPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ViewPatientInfo.class);
                startActivity(intent);
            }
        });
    }

    public void createPatientButton(View view) {
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.lastName);
        department = (EditText)findViewById(R.id.department);
        nurseID = (EditText)findViewById(R.id.nurseID);
        room = (EditText)findViewById(R.id.room);
        if (firstName.getText().toString().length() != 0 && lastName.getText().toString().length() != 0
                && department.getText().toString().length() != 0 &&
                nurseID.getText().toString().length() != 0 &&
                room.getText().toString().length() != 0) {
            String firstNameValue = firstName.getText().toString();
            String lastNameValue = lastName.getText().toString();
            String departmentValue = department.getText().toString();
            int nurseIDValue = Integer.parseInt(nurseID.getText().toString());
            String roomValue = room.getText().toString();

            patientViewModel.insert(new Patient(firstNameValue, lastNameValue, departmentValue, nurseIDValue, roomValue));
        }
        else {
            Toast.makeText(PatientActivity.this, "Please ensure there are no null values", Toast.LENGTH_SHORT).show();
        }
    }
}
