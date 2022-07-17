package com.furkankurt.dogbreeds.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.furkankurt.dogbreeds.Adapter.GridAdapter_Image_Display;
import com.furkankurt.dogbreeds.Adapter.RecyclerViewAdapter;
import com.furkankurt.dogbreeds.Api.DogBreedApi;
import com.furkankurt.dogbreeds.Api.DogImageApi;
import com.furkankurt.dogbreeds.Model.DogBreedsModel;
import com.furkankurt.dogbreeds.Model.DogImageModel;
import com.furkankurt.dogbreeds.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayScreen extends AppCompatActivity {
    private String BASE_URL = "https://dog.ceo/api/";
    Retrofit retrofit;
    GridView gridView;
    ProgressBar progressBar;
    List<DogImageModel> dogImageModel=new ArrayList<>();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_screen);
        gridView=findViewById(R.id.GridImageBreed);
        textView=findViewById(R.id.textView_display);
        Gson gson = new GsonBuilder().setLenient().create();
        progressBar=findViewById(R.id.prgLoading);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadImageAnaIrk();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DisplayScreen.this,DetailScreen.class);
                intent.putExtra("pos", dogImageModel.get(position));
                startActivity(intent);

            }
        });
    }
    private void loadImageAnaIrk() {
        final DogImageApi DogImageApi = retrofit.create(DogImageApi.class);
        String ırk=getIntent().getStringExtra("ırk");
        String ana_ırk_url="https://dog.ceo/api/breed/"+ırk+"/images";
        Call<DogImageModel> call = DogImageApi.getImage(ana_ırk_url);
        call.enqueue(new Callback<DogImageModel>() {
            @Override
            public void onResponse(Call<DogImageModel> call, Response<DogImageModel> response) {
                if (response.isSuccessful()) {
                    textView.setText(ırk.toUpperCase()+" BREED IMAGES");
                    List<String> asd=response.body().getImage_message();
                    dogImageModel = new ArrayList(asd);
                    GridAdapter_Image_Display adapter=new GridAdapter_Image_Display(dogImageModel,DisplayScreen.this);
                    gridView.setAdapter(adapter);
                }
                progressBar.setVisibility(View.INVISIBLE);
                gridView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<DogImageModel> call, Throwable t) {
                System.out.println("HATA"+t);
            }
        });
    }
}