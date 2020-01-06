package com.voyager.movielist.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.voyager.movielist.R;
import com.voyager.movielist.activity.adapter.MovieListAdapter;
import com.voyager.movielist.activity.model.Results;
import com.voyager.movielist.activity.view.MovieListViewModel;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout srlMovieList;
    private MovieListViewModel movieListViewModel;

    MovieListAdapter mMovieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initializationViews();
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        getTestList();
        // lambda expression
        srlMovieList.setOnRefreshListener(() -> {
            getTestList();
        });
    }

    public void getTestList() {
        srlMovieList.setRefreshing(true);
        movieListViewModel.getAllMovieList().observe(this, new Observer<ArrayList<Results>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Results> results) {
                srlMovieList.setRefreshing(false);
                prepareRecyclerView(results);
            }
        });
    }

    private void initializationViews() {
        srlMovieList = findViewById(R.id.srlMovieList);
        mRecyclerView = findViewById(R.id.rvMovieList);
    }


    private void prepareRecyclerView(ArrayList<Results> results) {

        mMovieListAdapter = new MovieListAdapter(results);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        //mListAdapter.setTestList(testLists);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMovieListAdapter);
        mMovieListAdapter.notifyDataSetChanged();

    }

}
