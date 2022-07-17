package com.furkankurt.dogbreeds.Api;

import com.furkankurt.dogbreeds.Model.DogBreedsModel;
import com.furkankurt.dogbreeds.Model.DogImageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DogImageApi {
        @GET
        Call<DogImageModel> getImage(
        @Url String url
        );
}
