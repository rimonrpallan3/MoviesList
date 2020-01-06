package com.voyager.movielist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.voyager.movielist.R;
import com.voyager.movielist.activity.model.Results;

public class MProfileActivity extends AppCompatActivity {

    ImageView ivThumbnail;
    TextView tvTitle;
    TextView tvDescription;
    TextView tvLink;
    Bundle bundle;
    Results results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mprofile);

        ActionBar actionBar = getSupportActionBar();


        ivThumbnail = findViewById(R.id.ivThumbnail);
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvLink = findViewById(R.id.tvLink);

        Intent intent = getIntent();
        bundle = new Bundle();
        results = (Results) intent.getParcelableExtra("Results");
        if (results != null) {
            System.out.println("MProfileActivity -- Results- title : " + results.getTitle());
            System.out.println("MProfileActivity -- Results- producer : " + results.getProducer());
            System.out.println("MProfileActivity -- Results- director : " + results.getDirector());
            System.out.println("MProfileActivity -- Results- release date : " + results.getRelease_date());
            actionBar.setTitle(results.getTitle());
            Glide.with(this)
                    .load(R.drawable.ic_local_movies)
                    .into(ivThumbnail);
            tvTitle.setText(results.getTitle());
            tvDescription.setText(results.getOpening_crawl());
            tvLink.setText(" Producer : "+ results.getProducer());
        } else {
            System.out.println("MProfileActivity -- Results : Is Null " );
        }

    }
}
