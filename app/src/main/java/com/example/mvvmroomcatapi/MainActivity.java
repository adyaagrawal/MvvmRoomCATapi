package com.example.mvvmroomcatapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private CatViewModel catViewModel;
    private List<Model> getCats;
    private CatAdapter catAdapter;
    private RecyclerView recyclerView;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository=new Repository(getApplication());
        getCats=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);


        catViewModel=new ViewModelProvider(this).get(CatViewModel.class);

        catAdapter=new CatAdapter(this, getCats);
        makeRequest();
        catViewModel.getAllCats().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> cats) {
                recyclerView.setAdapter(catAdapter);
                catAdapter.getAllDatas(cats);
                Log.d("main", "onChanged: "+cats);
            }
        });
    }

    private void makeRequest() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/images/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CATapi api=retrofit.create(CATapi.class);
        Call<List<Model>> call=api.getImgs(10);
        call.enqueue(new retrofit2.Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if(response.isSuccessful()) {
                    repository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.d("main", "onFailure: "+t.getMessage());
            }
        });
    }
}