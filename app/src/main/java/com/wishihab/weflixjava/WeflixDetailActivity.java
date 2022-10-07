package com.wishihab.weflixjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;

import com.wishihab.weflixjava.databinding.ActivityWeflixDetailBinding;
import com.wishihab.weflixjava.view.movie.MovieDetailFragment;
import com.wishihab.weflixjava.view.person.PersonDetailFragment;
import com.wishihab.weflixjava.view.youtube.YoutubeModuleFragment;

public class WeflixDetailActivity extends AppCompatActivity implements YoutubeModuleFragment.Listener {
    private static final String TAG_DETAIL = "weflix_movie_detail";
    private static final String ARG_DETAIL_TYPE = "detail_type";
    private static final String ARG_MOVIE_ID = "movie_id";
    private static final String ARG_MOVIE_TITLE = "movie_title";
    private static final String ARG_PERSON_ID = "person_id";

    private ActivityWeflixDetailBinding binding;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WeflixDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        binding = ActivityWeflixDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if(intent.getStringExtra(ARG_DETAIL_TYPE).equals("movies")){
            initFragment(intent.getStringExtra(ARG_MOVIE_ID), intent.getStringExtra(ARG_MOVIE_TITLE));
        }else if(intent.getStringExtra(ARG_DETAIL_TYPE).equals("tv")){

        }else{
            initFragmentPerson(intent.getStringExtra(ARG_PERSON_ID));
        }
    }


    private void initFragment(String movieId, String movieTitle) {
        FragmentManager manager = getSupportFragmentManager();
        MovieDetailFragment movieDetailFragment = (MovieDetailFragment) manager.findFragmentByTag(TAG_DETAIL);
        if (movieDetailFragment == null) {
            movieDetailFragment = MovieDetailFragment.newInstance(movieId,movieTitle);
            manager.beginTransaction()
                    .replace(R.id.content_view, movieDetailFragment, TAG_DETAIL)
                    .commit();
        }
    }

    private void initFragmentPerson(String personId) {
        FragmentManager manager = getSupportFragmentManager();
        PersonDetailFragment personDetailFragment = (PersonDetailFragment) manager.findFragmentByTag(TAG_DETAIL);
        if (personDetailFragment == null) {
            personDetailFragment = PersonDetailFragment.newInstance(personId, "");
            manager.beginTransaction()
                    .replace(R.id.content_view, personDetailFragment, TAG_DETAIL)
                    .commit();
        }
    }

    @Override
    public void onDismissedCall() {
    }
}