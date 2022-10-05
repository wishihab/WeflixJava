package com.wishihab.weflixjava.view.person;

import com.wishihab.weflixjava.model.general.person.PersonPopularResult;
import com.wishihab.weflixjava.model.general.person.PersonPopularViewState;

import java.util.List;

public interface PersonView {

    void showDataPerson(List<PersonPopularResult> data);

    void showProgressPerson(boolean progress);

    void showMessagePerson(String message);

    default void apply(PersonPopularViewState viewState){
        showProgressPerson(viewState.isProgress());

        if(viewState.hasData()){
            showDataPerson(viewState.getPersonPopularResult());
        }

        if(viewState.hasErrorMessage()){
            showMessagePerson(viewState.getErrorMessage());
        }
    }
}
