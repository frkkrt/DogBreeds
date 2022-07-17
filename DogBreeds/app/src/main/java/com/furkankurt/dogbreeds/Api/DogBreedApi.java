package com.furkankurt.dogbreeds.Api;

import com.furkankurt.dogbreeds.Model.DogBreedsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogBreedApi {
    @GET("breeds/list/all")
    Call<DogBreedsModel> getData();
}
