package com.voyager.movielist.activity.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.voyager.movielist.activity.model.Results;
import com.voyager.movielist.activity.repository.MovieListsRepository;

import java.util.ArrayList;

public class MovieListViewModel extends AndroidViewModel {

    private MovieListsRepository movieListsRepository;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
        movieListsRepository = new MovieListsRepository(application);
    }

    public LiveData<ArrayList<Results>> getAllMovieList() {
        return movieListsRepository.getMutableLiveData();
    }
}
