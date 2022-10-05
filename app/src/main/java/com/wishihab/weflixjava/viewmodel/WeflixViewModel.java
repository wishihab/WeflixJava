package com.wishihab.weflixjava.viewmodel;

import androidx.lifecycle.LiveData;

import com.wishihab.weflixjava.model.general.MoviePopularViewState;
import com.wishihab.weflixjava.model.general.PersonPopularViewState;
import com.wishihab.weflixjava.model.general.TvPopularViewState;

public interface WeflixViewModel {
    LiveData<MoviePopularViewState> getMovieViewState();
    LiveData<TvPopularViewState> getTvViewState();
    LiveData<PersonPopularViewState> getPersonViewState();

    void refresh();
    void refreshMovie();
    void refreshTv();
    void refreshPerson();
}
