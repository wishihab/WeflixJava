package com.wishihab.weflixjava;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerActivity extends YouTubeBaseActivity {

    private static final String ARG_YOUTUBE_ID = "youtube_id";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, YoutubePlayerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        Intent intent = getIntent();
        String apiKey = "AIzaSyBNggAqYJVmKn-p7D_DrlWyX3Rc24fv0uI";

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.ytPlayer);
        youTubePlayerView.initialize(apiKey, new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(intent.getStringExtra(ARG_YOUTUBE_ID));
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                //error
            }
        });
    }
}