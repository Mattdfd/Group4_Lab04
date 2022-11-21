package com.example.group4_comp304sec004_lab04;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class ViewPatientInfo extends AppCompatActivity {
    PatientViewModel patientViewModel;
    TextView patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);
        setTitle("Patient List");

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientList = (TextView) findViewById(R.id.patientList);

        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@Nullable List<Patient> patients) {
                String output = "";
                for (Patient patient : patients) {
                    output += "Id: " + patient.getPatientID() + " | Name: " + patient.getFirstName() + " " + patient.getLastName() + " | Department: " + patient.getDepartment() + " | Nurse Id: "
                            + patient.getNurseID() + " | Room: " + patient.getRoom() + "\n";
                }
                patientList.setText(output);
            }
        });
    }

}