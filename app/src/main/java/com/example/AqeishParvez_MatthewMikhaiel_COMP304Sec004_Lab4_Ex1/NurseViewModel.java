package com.example.AqeishParvez_MatthewMikhaiel_COMP304Sec004_Lab4_Ex1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NurseViewModel extends AndroidViewModel{
    private NurseRepository nurseRepository;
    private LiveData<List<Nurse>> allNurses;

    public NurseViewModel(Application application) {
        super((application));
        nurseRepository = new NurseRepository(application);
        allNurses = nurseRepository.getAllPatients();
    }

    public  LiveData<Nurse> findByNurseID(int nurseID) {return nurseRepository.findbyNurseID(nurseID); }

    public void insert(Nurse nurse) { nurseRepository.insert(nurse); }

    public LiveData<List<Nurse>> getAllNurses() { return allNurses; }
}
