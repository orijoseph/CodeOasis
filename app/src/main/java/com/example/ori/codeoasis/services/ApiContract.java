package com.example.ori.codeoasis.services;

import com.example.ori.codeoasis.models.ResponseServer;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Ori on 1/25/2018.
 */

public interface ApiContract {
    @GET("contacts")
    Observable<ResponseServer> getList();
}
