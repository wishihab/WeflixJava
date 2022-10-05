package com.wishihab.weflixjava.view.movie;

import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.model.general.movie.MoviePopularViewState;

import java.util.List;

public interface MovieView {

    void showData(List<MoviePopularResult> data);

    void showProgress(boolean progress);

    void showMessage(String message);

    default void apply(MoviePopularViewState viewState){
        showProgress(viewState.isProgress());

        if(viewState.hasData()){
            showData(viewState.getMoviePopularResult());
        }

        if(viewState.hasErrorMessage()){
            showMessage(viewState.getErrorMessage());
        }
    }
}
