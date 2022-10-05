package com.wishihab.weflixjava.view.movie;

import com.wishihab.weflixjava.model.general.MovieDetailResponse;
import com.wishihab.weflixjava.model.general.MovieDetailViewState;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.model.general.movie.MoviePopularViewState;

import java.util.List;

public interface MovieDetailView {

    void showData(MovieDetailResponse data);

    void showProgress(boolean progress);

    void showMessage(String message);

    default void apply(MovieDetailViewState viewState){
        showProgress(viewState.isProgress());

        if(viewState.hasData()){
            showData(viewState.getMovieDetailResponse());
        }

        if(viewState.hasErrorMessage()){
            showMessage(viewState.getErrorMessage());
        }
    }
}
