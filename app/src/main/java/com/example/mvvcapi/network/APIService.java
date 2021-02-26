package com.example.mvvcapi.network;

import com.example.mvvcapi.model.Moviemodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("users")
    Call<List<Moviemodel>> getMovieList();
}
