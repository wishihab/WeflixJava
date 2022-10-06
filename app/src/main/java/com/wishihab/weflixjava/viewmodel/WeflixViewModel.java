package com.wishihab.weflixjava.viewmodel;

import androidx.lifecycle.LiveData;

import com.wishihab.weflixjava.model.general.YoutubeQueryViewState;
import com.wishihab.weflixjava.model.general.movie.MovieReviewViewState;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailViewState;
import com.wishihab.weflixjava.model.general.movie.MoviePopularViewState;
import com.wishihab.weflixjava.model.general.person.PersonPopularViewState;
import com.wishihab.weflixjava.model.general.tv.TvPopularViewState;

public interface WeflixViewModel {
    LiveData<TvPopularViewState> getTvViewState();
    LiveData<PersonPopularViewState> getPersonViewState();

    void refresh();
    void refreshMovie();
    void refreshTv();
    void refreshPerson();


    LiveData<MoviePopularViewState> getMovieViewState();
    LiveData<MovieDetailViewState> getMovieDetailViewState();
    LiveData<MovieReviewViewState> getMovieReviewViewState();
    void doGetMovieDetail(String movieId);
    void doGetMovieReview(String movieId);

    LiveData<YoutubeQueryViewState> getYoutubeIdViewState();
    void doGetYoutubeId(String movieName, String apiKey);
}
