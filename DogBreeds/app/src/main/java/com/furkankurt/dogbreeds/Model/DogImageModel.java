package com.furkankurt.dogbreeds.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DogImageModel implements Serializable {
    public List<String> getImage_message() {
        return image_message;
    }

    public void setImage_message(List<String> image_message) {
        this.image_message = image_message;
    }

    public String getImage_status() {
        return image_status;
    }

    public void setImage_status(String image_status) {
        this.image_status = image_status;
    }

    @SerializedName("message")
    @Expose
    private List<String> image_message;
    @SerializedName("status")
    @Expose
    private String image_status;



}
