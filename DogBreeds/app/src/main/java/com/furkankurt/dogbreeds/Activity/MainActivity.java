package com.furkankurt.dogbreeds.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.furkankurt.dogbreeds.Adapter.RecyclerViewAdapter;
import com.furkankurt.dogbreeds.Api.DogBreedApi;
import com.furkankurt.dogbreeds.Fragment.SplashScreen;
import com.furkankurt.dogbreeds.Model.DogBreedsModel;
import com.furkankurt.dogbreeds.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String BASE_URL = "https://dog.ceo/api/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ProgressBar progressBar;
    public static Map<String, List<String>> map;
    List<DogBreedsModel> dogbreeds=new ArrayList<>();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView_content);
        SharedPreferences ayarlar = this.getSharedPreferences("Splash", MODE_PRIVATE);
        int a = ayarlar.getInt("first", 0);
        if(a==0)
        {
            FragmentAc(new SplashScreen());
            progressBar.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
        }
        else if(a==4)
        {
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            ayarlar.edit().clear().commit();
            recyclerView = findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            loadData();

        }
        progressBar = findViewById(R.id.progressBar);


    }
    private void loadData() {
        final DogBreedApi DogBreedsApi = retrofit.create(DogBreedApi.class);
        Call<DogBreedsModel> call = DogBreedsApi.getData();
        call.enqueue(new Callback<DogBreedsModel>() {
            @Override
            public void onResponse(Call<DogBreedsModel> call, Response<DogBreedsModel> response) {
                if (response.isSuccessful()) {
                    map = response.body().getMessage();
                    dogbreeds = new ArrayList(map.keySet());
                    recyclerViewAdapter = new RecyclerViewAdapter(dogbreeds,MainActivity.this);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<DogBreedsModel> call, Throwable t) {

            }
        });
    }




    private void FragmentAc(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_container,fragment);
        fragmentTransaction.commit();

    }

}