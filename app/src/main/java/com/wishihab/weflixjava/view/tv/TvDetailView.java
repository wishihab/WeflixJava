package com.wishihab.weflixjava.view.tv;

import com.wishihab.weflixjava.model.general.tv.detail.TvDetailResult;
import com.wishihab.weflixjava.model.general.tv.detail.TvDetailViewState;

public interface TvDetailView {

    void showDataTvDetail(TvDetailResult data);

    void showProgressTvDetail(boolean progress);

    void showMessageTvDetail(String message);

    default void apply(TvDetailViewState viewState){
        showProgressTvDetail(viewState.isProgress());

        if(viewState.hasData()){
            showDataTvDetail(viewState.getTvDetailResult());
        }

        if(viewState.hasErrorMessage()){
            showMessageTvDetail(viewState.getErrorMessage());
        }
    }

}
