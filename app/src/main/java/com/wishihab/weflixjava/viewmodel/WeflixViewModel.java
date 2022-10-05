package com.wishihab.weflixjava.viewmodel;

import androidx.lifecycle.LiveData;

import com.wishihab.weflixjava.model.general.MoviePopularViewState;

public interface WeflixViewModel {
    LiveData<MoviePopularViewState> getMovieViewState();

    void refreshMovie();
}
