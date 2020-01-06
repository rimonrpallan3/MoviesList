package com.voyager.movielist.apiService;

import com.voyager.movielist.activity.model.MovieListsWrapper;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface  RestApiService {

    @GET("films/")
    Call<MovieListsWrapper> getMovieList();
}
