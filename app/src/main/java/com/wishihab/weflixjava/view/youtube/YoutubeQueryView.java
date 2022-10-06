package com.wishihab.weflixjava.view.youtube;

import com.wishihab.weflixjava.model.general.YoutubeQueryResult;
import com.wishihab.weflixjava.model.general.YoutubeQueryViewState;
import com.wishihab.weflixjava.model.general.movie.MovieReviewResult;
import com.wishihab.weflixjava.model.general.movie.MovieReviewViewState;

import java.util.List;

public interface YoutubeQueryView {

    void showDataYoutube(List<YoutubeQueryResult> data);

    void showProgressYoutube(boolean progress);

    void showMessageYoutube(String message);

    default void apply(YoutubeQueryViewState viewState){
        showProgressYoutube(viewState.isProgress());

        if(viewState.hasData()){
            showDataYoutube(viewState.getYoutubeQueryResults());
        }

        if(viewState.hasErrorMessage()){
            showMessageYoutube(viewState.getErrorMessage());
        }
    }
}
