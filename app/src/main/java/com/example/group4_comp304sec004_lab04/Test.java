package com.example.group4_comp304sec004_lab04;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table")
public class Test {

    @PrimaryKey(autoGenerate = true)
    private int testId;

    @NonNull
    private int patientId;

    @NonNull
    private int nurseId;

    @NonNull
    private int BPL;

    @NonNull
    private int BPH;

    @NonNull
    private int temperature;

    public Test(@NonNull int patientId,@NonNull int nurseId,@NonNull int BPL,@NonNull int BPH,@NonNull int temperature) {
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperature = temperature;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @NonNull
    public int getPatientId() {
        return patientId;
    }

    @NonNull
    public int getNurseId() {
        return nurseId;
    }

    @NonNull
    public int getBPL() {
        return BPL;
    }

    @NonNull
    public int getBPH() {
        return BPH;
    }


    @NonNull
    public int getTemperature() {
        return temperature;
    }

}
