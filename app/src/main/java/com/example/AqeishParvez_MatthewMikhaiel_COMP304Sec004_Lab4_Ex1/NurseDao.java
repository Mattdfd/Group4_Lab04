package com.example.AqeishParvez_MatthewMikhaiel_COMP304Sec004_Lab4_Ex1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NurseDao {
    @Insert
    void insert(Nurse nurse);

    @Update
    void update(Nurse nurse);

    @Delete
    void delete(Nurse nurse);

    @Query("DELETE FROM nurse_table")
    void deleteAll();

    @Query("Select * FROM nurse_table where nurseID = :nurseID")
    LiveData<Nurse> getByNurseID(int nurseID);

    @Query("Select * FROM nurse_table")
    LiveData<List<Nurse>> getAllNurses();
}
