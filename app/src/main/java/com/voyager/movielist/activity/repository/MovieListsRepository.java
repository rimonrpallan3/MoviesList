package com.voyager.movielist.activity.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;


import com.voyager.movielist.activity.model.MovieListsWrapper;
import com.voyager.movielist.activity.model.Results;
import com.voyager.movielist.apiService.RestApiService;
import com.voyager.movielist.apiService.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListsRepository {
    private ArrayList<Results> movies = new ArrayList<>();
    private MutableLiveData<ArrayList<Results>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieListsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<ArrayList<Results>> getMutableLiveData() {

        RestApiService apiService = RetrofitInstance.getApiService();

        Call<MovieListsWrapper> call = apiService.getMovieList();

        call.enqueue(new Callback<MovieListsWrapper>() {
            @Override
            public void onResponse(Call<MovieListsWrapper> call, Response<MovieListsWrapper> response) {
                MovieListsWrapper mMovieListsWrapper = response.body();
                if (mMovieListsWrapper != null || mMovieListsWrapper.getResults() != null) {
                    movies = (ArrayList<Results>) mMovieListsWrapper.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieListsWrapper> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }
}
