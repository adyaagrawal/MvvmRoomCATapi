package com.example.mvvmroomcatapi;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class CatViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<Model>> getAllCats;

    public CatViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        getAllCats=repository.getAllCats();
    }

    public void insert(List<Model> cats){
        repository.insert(cats);
    }

    public LiveData<List<Model>> getAllCats()
    {
        return getAllCats;
    }
}
