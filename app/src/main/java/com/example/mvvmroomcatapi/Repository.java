package com.example.mvvmroomcatapi;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class Repository {
    public catimgDao catimgDao;
    public LiveData<List<Model>> getAllCats;
    private CatDatabase database;

    public Repository(Application application){
        database=CatDatabase.getInstance(application);
        catimgDao=database.catimgDao();
        getAllCats=catimgDao.getcats();

    }

    public void insert(List<Model> cats){
        new InsertAsyncTask(catimgDao).execute(cats);

    }

    public LiveData<List<Model>> getAllCats(){
        return getAllCats;
    }
    private static class InsertAsyncTask extends AsyncTask<List<Model>,Void,Void>{
        private catimgDao catimgDao;

        public InsertAsyncTask(catimgDao catDao)
        {
            this.catimgDao=catDao;
        }
        @Override
        protected Void doInBackground(List<Model>... lists) {
            catimgDao.insert(lists[0]);
            return null;
        }
    }

}