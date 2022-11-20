package com.example.group4_comp304sec004_lab04;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nurse_table")
public class Nurse {

    @PrimaryKey(autoGenerate = true)
    private int nurseId;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String department;

    @NonNull
    private String password;

    public Nurse(@NonNull String firstname, @NonNull String lastname, @NonNull String department, @NonNull String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }


    @NonNull
    public String getLastname() {
        return lastname;
    }


    @NonNull
    public String getDepartment() {
        return department;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

}
