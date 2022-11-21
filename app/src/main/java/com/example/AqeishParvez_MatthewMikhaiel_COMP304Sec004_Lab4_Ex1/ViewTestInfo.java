package com.example.AqeishParvez_MatthewMikhaiel_COMP304Sec004_Lab4_Ex1;

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
import java.util.concurrent.atomic.AtomicBoolean;

public class ViewTestInfo extends AppCompatActivity {
    private TestViewModel testViewModel;
    private EditText patientID;
    private TextView testList;
    Button viewTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);
        setTitle("Find Test Page");

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        patientID = (EditText) findViewById(R.id.enterPatientIdVT);
        viewTests = (Button) findViewById(R.id.viewTestVT);

        viewTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findTestInfo();
            }
        });
    }

    public void findTestInfo(){
        AtomicBoolean found = new AtomicBoolean(false);
        testList = (TextView) findViewById(R.id.testListVTI);
        if(patientID.getText().toString().isEmpty()){
            testList.setText("Please enter a patient ID");
            Toast.makeText(this, "Patient Id Needed", Toast.LENGTH_SHORT).show();
        } else {
            int id = Integer.parseInt(patientID.getText().toString());
            testViewModel.getAllTests().observe(this, (Observer<List<Test>>) tests -> {
                for (Test test : tests) {
                    if (test.getPatientId() == id) {
                        found.set(true);
                        testViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
                            @Override
                            public void onChanged(@Nullable List<Test> tests) {
                                String output = "Test ID: " + test.getTestId() + " | Patient ID: " + test.getPatientId() + " | Nurse ID: " + test.getNurseId() + " | BPL: "
                                        + test.getBPL() + " | BPH: " + test.getBPH() + " | Temprature: " + test.getTemperature() + "\n";
                                testList.setText(output);
                            }
                        });
                        break;
                    } else {
                        testList.setText("Not Tests Found");
                    }
                }

                if (found.get()) {
                    Toast.makeText(this, "Test Found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Test Not Found", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}