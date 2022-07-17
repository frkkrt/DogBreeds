package com.furkankurt.dogbreeds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.furkankurt.dogbreeds.Model.DogImageModel;
import com.furkankurt.dogbreeds.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailScreen extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_detail_screen);
            imageView=findViewById(R.id.ImageDetail);
            String deger=getIntent().getStringExtra("pos");
            Picasso.get().load(deger).into(imageView);
    }
}