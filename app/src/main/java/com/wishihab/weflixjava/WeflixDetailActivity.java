package com.wishihab.weflixjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;

import com.wishihab.weflixjava.databinding.ActivityMainBinding;
import com.wishihab.weflixjava.databinding.ActivityWeflixDetailBinding;
import com.wishihab.weflixjava.view.WeflixFragmentHome;
import com.wishihab.weflixjava.view.WeflixMovieDetailFragment;

public class WeflixDetailActivity extends AppCompatActivity {
    private static final String TAG_DETAIL = "weflix_movie_detail";
    private static final String ARG_MOVIE_ID = "movie_id";

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

        initFragment(intent.getStringExtra(ARG_MOVIE_ID));
    }


    private void initFragment(String movieId) {
        FragmentManager manager = getSupportFragmentManager();
        WeflixMovieDetailFragment weflixMovieDetailFragment = (WeflixMovieDetailFragment) manager.findFragmentByTag(TAG_DETAIL);
        if (weflixMovieDetailFragment == null) {
            weflixMovieDetailFragment = WeflixMovieDetailFragment.newInstance(movieId,"");
            manager.beginTransaction()
                    .replace(R.id.content_view, weflixMovieDetailFragment, TAG_DETAIL)
                    .commit();
        }
    }

}