package com.example.mvvcapi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitinstance {

    public static String BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/";

    private static Retrofit retrofit;   //instance variable of retrofit

    public static Retrofit getRetroClient(){

        if(retrofit == null){                  // Singleton

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}