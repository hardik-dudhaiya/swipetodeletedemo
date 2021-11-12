package com.example.testandroid.interfaces;

import com.example.testandroid.models.CommonData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("test.json")
    Call<CommonData> getData();
}
