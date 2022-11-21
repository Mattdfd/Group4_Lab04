package com.example.group4_comp304sec004_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class TestActivity extends AppCompatActivity {
    private TestViewModel testViewModel;
    private EditText patientID;
    private EditText nurseID;
    private EditText bpl;
    private EditText bph;
    private EditText temprature;
    Button addTest, viewTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);

        addTest = findViewById(R.id.addTest);
        viewTests = findViewById(R.id.viewTest);

        viewTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ViewTestInfo.class);
                startActivity(intent);
            }
        });

        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTestButton();
            }
        });
    }

    public void addTestButton() {
        patientID = (EditText) findViewById(R.id.patientIDTest);
        nurseID = (EditText)findViewById(R.id.nurseIDTest);
        bpl = (EditText)findViewById(R.id.bpl);
        bph = (EditText)findViewById(R.id.bph);
        temprature = (EditText)findViewById(R.id.temprature);
        if (patientID.getText().toString().length() != 0 && nurseID.getText().toString().length() != 0
                && bpl.getText().toString().length() != 0 &&
                bph.getText().toString().length() != 0 &&
                temprature.getText().toString().length() != 0) {
            int patientIdValue = Integer.parseInt(patientID.getText().toString());
            int nurseIdValue = Integer.parseInt(nurseID.getText().toString());
            int bplValue = Integer.parseInt(bpl.getText().toString());
            int bphValue = Integer.parseInt(bph.getText().toString());
            int tempratureValue = Integer.parseInt(temprature.getText().toString());

            testViewModel.insert(new Test(patientIdValue,nurseIdValue,bplValue,bphValue,tempratureValue));
        }
        else {
            Toast.makeText(TestActivity.this, "Please ensure there are no null values", Toast.LENGTH_SHORT).show();
        }
    }
}