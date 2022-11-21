package com.example.group4_comp304sec004_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class UpdatePatientInfo extends AppCompatActivity {
    private PatientViewModel patientViewModel;
    private EditText patientID, firstName, lastName, department, nurseID, room;
    Button viewPatients, updatePatient, enterTestsScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient_info);
        setTitle("Update Patient Info");

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        updatePatient = (Button) findViewById(R.id.updatePatient);
        viewPatients = (Button) findViewById(R.id.viewPatientUI);
        enterTestsScreen = (Button) findViewById(R.id.enterTestUI);

        enterTestsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TestActivity.class);
                startActivity(intent);
            }
        });

        viewPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ViewPatientInfo.class);
                startActivity(intent);
            }
        });
    }

    public void updatePatientButton(View view) {
        patientID = (EditText) findViewById(R.id.patientIdUI);
        firstName = (EditText) findViewById(R.id.firstNameUI);
        lastName = (EditText)findViewById(R.id.lastNameUI);
        department = (EditText)findViewById(R.id.departmentUI);
        nurseID = (EditText)findViewById(R.id.nurseIDUI);
        room = (EditText)findViewById(R.id.roomUI);

        if (patientID.getText().toString().length()!=0 && firstName.getText().toString().length() != 0 && lastName.getText().toString().length() != 0
                && department.getText().toString().length() != 0 &&
                nurseID.getText().toString().length() != 0 &&
                room.getText().toString().length() != 0) {
            int patientIDValue = Integer.parseInt(patientID.getText().toString());
            String firstNameValue = firstName.getText().toString();
            String lastNameValue = lastName.getText().toString();
            String departmentValue = department.getText().toString();
            int nurseIDValue = Integer.parseInt(nurseID.getText().toString());
            String roomValue = room.getText().toString();

            patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
                @Override
                public void onChanged(@Nullable List<Patient> patients) {
                    for (Patient patient : patients) {
                        if (patientIDValue==patient.getPatientID())
                        {
                            Patient newPatient = new Patient(firstNameValue,lastNameValue,departmentValue,nurseIDValue,roomValue);
                            newPatient.setPatientID(patientIDValue);
                            patientViewModel.update(newPatient);
                            break;
                        } else {
                            Toast.makeText(UpdatePatientInfo.this, "No Patient With This ID Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


        }
        else {
            Toast.makeText(UpdatePatientInfo.this, "Please ensure there are no null values", Toast.LENGTH_SHORT).show();
        }
    }
}