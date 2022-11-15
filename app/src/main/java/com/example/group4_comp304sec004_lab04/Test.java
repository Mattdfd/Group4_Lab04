package com.example.group4_comp304sec004_lab04;

import android.arch.persistence.room.Entity;

@Entity
public class Test {
    private int testId;
    private int patientId;
    private int nurseId;
    private int BPL;
    private int BPH;
    private int temperature;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getBPL() {
        return BPL;
    }

    public void setBPL(int BPL) {
        this.BPL = BPL;
    }

    public int getBPH() {
        return BPH;
    }

    public void setBPH(int BPH) {
        this.BPH = BPH;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
