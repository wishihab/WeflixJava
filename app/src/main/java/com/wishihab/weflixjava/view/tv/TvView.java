package com.wishihab.weflixjava.view.tv;

import com.wishihab.weflixjava.model.general.tv.TvPopularResult;
import com.wishihab.weflixjava.model.general.tv.TvPopularViewState;

import java.util.List;

public interface TvView {

    void showDataTv(List<TvPopularResult> data);

    void showProgressTv(boolean progress);

    void showMessageTv(String message);

    default void apply(TvPopularViewState viewState){
        showProgressTv(viewState.isProgress());

        if(viewState.hasData()){
            showDataTv(viewState.getTvPopularResult());
        }

        if(viewState.hasErrorMessage()){
            showMessageTv(viewState.getErrorMessage());
        }
    }
}
