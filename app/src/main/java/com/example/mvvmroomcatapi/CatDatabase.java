package com.example.mvvmroomcatapi;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Model.class},version = 5)
public abstract class CatDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Cat";
    public abstract catimgDao catimgDao();
    public static volatile CatDatabase INSTANCE = null;

    public static CatDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CatDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, CatDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyn(INSTANCE);
        }
    };

    static  class  PopulateDbAsyn extends AsyncTask<Void,Void,Void>{
        private catimgDao catimgDao;
        public PopulateDbAsyn(CatDatabase catDatabase)
        {
            catimgDao=catDatabase.catimgDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            catimgDao.deleteAll();
            return null;
        }
    }
}