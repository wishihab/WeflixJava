package com.wishihab.weflixjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;

import com.wishihab.weflixjava.databinding.ActivityListMainBinding;
import com.wishihab.weflixjava.databinding.ActivityMainBinding;
import com.wishihab.weflixjava.view.WeflixFragmentHome;
import com.wishihab.weflixjava.view.WeflixListMainFragment;

public class WeflixListActivity extends AppCompatActivity {
    private static final String TAG_MAIN_LIST = "weflix_list_main_tag";
    private static final String ARG_TYPE_ID = "TYPE_ID";

    private ActivityListMainBinding binding;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WeflixListActivity.class);
        return intent;
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
        binding = ActivityListMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.appBar.upButton.setOnClickListener(v-> finish());
        Intent intent = getIntent();

        initFragment(intent.getStringExtra(ARG_TYPE_ID));
    }


    private void initFragment(String type) {
        FragmentManager manager = getSupportFragmentManager();
        WeflixListMainFragment fragment = (WeflixListMainFragment) manager.findFragmentByTag(TAG_MAIN_LIST);
        if(fragment == null){
            fragment = WeflixListMainFragment.newInstance(type,"");
            manager.beginTransaction()
                    .replace(R.id.content_view, fragment, TAG_MAIN_LIST)
                    .addToBackStack(TAG_MAIN_LIST)
                    .commit();
        }
    }

}