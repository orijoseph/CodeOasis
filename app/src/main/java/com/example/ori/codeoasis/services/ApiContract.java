package com.example.ori.codeoasis.services;

import com.example.ori.codeoasis.models.ResponseServer;

import retrofit2.Call;

import retrofit2.http.GET;

/**
 * Created by Ori on 1/25/2018.
 */

public interface ApiContract {
    @GET("contacts")
    Call<ResponseServer> getList();
}
