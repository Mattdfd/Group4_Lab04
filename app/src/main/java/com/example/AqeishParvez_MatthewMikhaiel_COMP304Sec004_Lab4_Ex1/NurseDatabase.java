package com.example.AqeishParvez_MatthewMikhaiel_COMP304Sec004_Lab4_Ex1;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Nurse.class}, version = 1, exportSchema = false)
public abstract class NurseDatabase extends RoomDatabase {

    public abstract NurseDao nurseDao();

    private static volatile NurseDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static NurseDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NurseDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    NurseDatabase.class, "nurse_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more patients, just add them.
//                NurseDao dao = INSTANCE.nurseDao();
//                dao.deleteAll();
//            });
        }
    };

}
