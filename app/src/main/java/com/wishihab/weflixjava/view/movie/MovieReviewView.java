package com.wishihab.weflixjava.view.movie;

import com.wishihab.weflixjava.model.general.movie.MovieReviewResponse;
import com.wishihab.weflixjava.model.general.movie.MovieReviewResult;
import com.wishihab.weflixjava.model.general.movie.MovieReviewViewState;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailResponse;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailViewState;

import java.util.List;

public interface MovieReviewView {

    void showDataReview(List<MovieReviewResult> data);

    void showProgressReview(boolean progress);

    void showMessageReview(String message);

    default void apply(MovieReviewViewState viewState){
        showProgressReview(viewState.isProgress());

        if(viewState.hasData()){
            showDataReview(viewState.getMovieReviewResults());
        }

        if(viewState.hasErrorMessage()){
            showMessageReview(viewState.getErrorMessage());
        }
    }
}
