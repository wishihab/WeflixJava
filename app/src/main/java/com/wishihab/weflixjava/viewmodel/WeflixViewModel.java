package com.wishihab.weflixjava.viewmodel;

import androidx.lifecycle.LiveData;

import com.wishihab.weflixjava.model.general.YoutubeQueryViewState;
import com.wishihab.weflixjava.model.general.movie.MovieReviewViewState;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailViewState;
import com.wishihab.weflixjava.model.general.movie.MoviePopularViewState;
import com.wishihab.weflixjava.model.general.person.PersonPopularViewState;
import com.wishihab.weflixjava.model.general.person.detail.PersonDetailViewState;
import com.wishihab.weflixjava.model.general.tv.TvPopularViewState;
import com.wishihab.weflixjava.model.general.tv.detail.TvDetailViewState;

public interface WeflixViewModel {
    LiveData<TvPopularViewState> getTvViewState();
    LiveData<TvDetailViewState> getTvDetailViewState();
    void doGetTvDetail(String tvId);


    LiveData<PersonPopularViewState> getPersonViewState();
    LiveData<PersonDetailViewState> getPersonDetailViewstate();
    void doGetPersonDetail(String personId);

    void refresh();
    void refreshMovie();
    void refreshTv();
    void refreshPerson();


    LiveData<MoviePopularViewState> getMovieViewState();
    void doGetMoviePage(Integer page);
    LiveData<MovieDetailViewState> getMovieDetailViewState();
    LiveData<MovieReviewViewState> getMovieReviewViewState();
    void doGetMovieDetail(String movieId);
    void doGetMovieReview(String movieId);

    LiveData<YoutubeQueryViewState> getYoutubeIdViewState();
    void doGetYoutubeId(String movieName, String apiKey);
}
