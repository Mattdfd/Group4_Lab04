package com.example.AqeishParvez_MatthewMikhaiel_COMP304Sec004_Lab4_Ex1;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TestRepository {
    public TestDao testDao;
    private LiveData<List<Test>> allTests;

    public TestRepository(Application application)
    {
        TestDatabase db = TestDatabase.getDatabase(application);
        testDao = db.testDao();
        allTests = testDao.getAllTests();
    }

    public LiveData<List<Test>> getAllTests() {return allTests; }

    public void insert(Test test) {
        NurseDatabase.databaseWriteExecutor.execute(() -> {
            testDao.insert(test);
        });
    }

    public LiveData<Test> findbyTestID(int testID) {return testDao.getByTestID(testID); }
}
