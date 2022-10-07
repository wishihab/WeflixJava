package com.wishihab.weflixjava.view.person;

import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailResponse;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailViewState;
import com.wishihab.weflixjava.model.general.person.PersonPopularViewState;
import com.wishihab.weflixjava.model.general.person.detail.PersonDetailResult;
import com.wishihab.weflixjava.model.general.person.detail.PersonDetailViewState;

public interface PersonDetailView {

    void showDataPersonDetail(PersonDetailResult data);

    void showProgressPersonDetail(boolean progress);

    void showMessagePersonDetail(String message);

    default void apply(PersonDetailViewState viewState){
        showProgressPersonDetail(viewState.isProgress());

        if(viewState.hasData()){
            showDataPersonDetail(viewState.getPersonDetailResult());
        }

        if(viewState.hasErrorMessage()){
            showMessagePersonDetail(viewState.getErrorMessage());
        }
    }

}
