package com.furkankurt.dogbreeds.Adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.furkankurt.dogbreeds.Model.DogImageModel;
import com.furkankurt.dogbreeds.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapter_Image_Display extends BaseAdapter {
    List<DogImageModel> imageModel;
    Activity activity;
    public GridAdapter_Image_Display(List<DogImageModel> imageModel, Activity activity) {
        this.imageModel = imageModel;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return imageModel.size();
    }

    @Override
    public Object getItem(int i) {
        return imageModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = LayoutInflater.from(activity).inflate(R.layout.dog_breed_image_row, parent, false);
        ImageView imageView = view.findViewById(R.id.DogImage);

        String url = String.valueOf(getItem(i));
        Picasso.get().load(url).into(imageView);
        return view;
    }
}
