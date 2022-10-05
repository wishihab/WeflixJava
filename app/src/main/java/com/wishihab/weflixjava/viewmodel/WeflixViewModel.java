package com.wishihab.weflixjava.viewmodel;

import androidx.lifecycle.LiveData;

import com.wishihab.weflixjava.model.general.MovieDetailViewState;
import com.wishihab.weflixjava.model.general.movie.MoviePopularViewState;
import com.wishihab.weflixjava.model.general.person.PersonPopularViewState;
import com.wishihab.weflixjava.model.general.tv.TvPopularViewState;

public interface WeflixViewModel {
    LiveData<MoviePopularViewState> getMovieViewState();
    LiveData<TvPopularViewState> getTvViewState();
    LiveData<PersonPopularViewState> getPersonViewState();

    void refresh();
    void refreshMovie();
    void refreshTv();
    void refreshPerson();


    LiveData<MovieDetailViewState> getMovieDetailViewState();
    void doGetMovieDetail(String movieId);
}
