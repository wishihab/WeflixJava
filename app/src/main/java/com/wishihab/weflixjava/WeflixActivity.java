package com.wishihab.weflixjava;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;

import com.wishihab.weflixjava.databinding.ActivityMainBinding;
import com.wishihab.weflixjava.view.WeflixFragmentHome;

import android.view.Menu;
import android.view.MenuItem;

public class WeflixActivity extends AppCompatActivity {
    private static final String TAG_MAIN = "weflix_tag";

    private ActivityMainBinding binding;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initFragment();
    }


    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        WeflixFragmentHome mainFragment = (WeflixFragmentHome) manager.findFragmentByTag(TAG_MAIN);
        if (mainFragment == null) {
            mainFragment = WeflixFragmentHome.newInstance();
            manager.beginTransaction()
                    .replace(R.id.content_view, mainFragment, TAG_MAIN)
                    .commit();
        }
    }

}