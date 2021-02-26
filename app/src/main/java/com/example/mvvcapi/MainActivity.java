package com.example.mvvcapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mvvcapi.adapter.Movielistadapter;
import com.example.mvvcapi.model.Moviemodel;
import com.example.mvvcapi.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Movielistadapter.ItemClickListener {

    RecyclerView recyclerView;
    private Movielistadapter movielistadapter;
    private List<Moviemodel> moviemodelList;
    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        final TextView noresult = findViewById(R.id.noResultTv);
        LinearLayoutManager LayoutManager = new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(LayoutManager);
        movielistadapter = new Movielistadapter(this,moviemodelList,this);
        recyclerView.setAdapter(movielistadapter);


        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        viewModel.getMovieListObserver().observe(this, new Observer<List<Moviemodel>>() {
            @Override
            public void onChanged(List<Moviemodel> movieModels) {
                if(movieModels != null) {
                    moviemodelList = movieModels;
                    movielistadapter.setMovielist(movieModels);
                    noresult.setVisibility(View.GONE);
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onMovieClick(Moviemodel movie) {
        Toast.makeText(this, "Clicked Movie Name is : " +movie.getAvatar(), Toast.LENGTH_SHORT).show();
    }
}