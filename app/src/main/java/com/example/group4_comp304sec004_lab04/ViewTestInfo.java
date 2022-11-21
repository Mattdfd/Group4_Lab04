package com.example.group4_comp304sec004_lab04;

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

public class ViewTestInfo extends AppCompatActivity {
    private TestViewModel testViewModel;
    private EditText patientID;
    private TextView testList;
    Button viewTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

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
        testList = (TextView) findViewById(R.id.testListVTI);
        int id = Integer.parseInt(patientID.getText().toString());
        testViewModel.getAllTests().observe(this, (Observer<List<Test>>) tests -> {
            for (Test test : tests)
            {
                if (test.getPatientId()==id){
                    Toast.makeText(this, "Test Found", Toast.LENGTH_SHORT).show();
                    testViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
                        @Override
                        public void onChanged(@Nullable List<Test> tests) {
                            String output = "";
                            for (Test test : tests) {
                                output += "Test ID: "+test.getTestId()+" | Patient ID: "+test.getPatientId() + " | Nurse ID: " + test.getNurseId() +" | BPL: "
                                        +test.getBPL()+" | BPH: "+test.getBPH()+" | Temprature: "+test.getTemperature()+"\n";
                            }
                            testList.setText(output);
                        }
                    });
                }else {
                    Toast.makeText(this, "Test Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}