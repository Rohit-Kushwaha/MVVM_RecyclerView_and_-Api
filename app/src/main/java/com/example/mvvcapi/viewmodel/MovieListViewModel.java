package com.example.mvvcapi.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvcapi.model.Moviemodel;
import com.example.mvvcapi.network.APIService;
import com.example.mvvcapi.network.Retrofitinstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    public MutableLiveData<List<Moviemodel>> movieList; //Observer Live data and update the recycler view so that when we get the response from the api then we get into the activity and from activity it will get into recycler view

    public MovieListViewModel() {
        movieList = new MutableLiveData<>();
    }

    //Seprate function to return live data
    public  MutableLiveData<List<Moviemodel>> getMovieListObserver(){
     return  movieList;
    }
    //Api Call from retrofit
    public void makeApiCall(){
        APIService apiService = Retrofitinstance.getRetroClient().create(APIService.class);
        Call<List<Moviemodel>> call = apiService.getMovieList();
        call.enqueue(new Callback<List<Moviemodel>>() {
            @Override
            public void onResponse(Call<List<Moviemodel>> call, Response<List<Moviemodel>> response) {
                //Call it when get the response
                movieList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Moviemodel>> call, Throwable t) {
                movieList.postValue(null);
            }
        });
    }

}
