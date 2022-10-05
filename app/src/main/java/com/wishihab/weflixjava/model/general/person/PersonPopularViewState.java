package com.wishihab.weflixjava.model.general.person;

import java.util.List;

public class PersonPopularViewState {

    /**
     * The enum State.
     */
    public enum State {
        NONE,
        REQUEST_SUCCESS,
        ERROR_MESSAGE
    }

    private State state;
    private List<PersonPopularResult> personPopularResults;
    private boolean progress;
    private String errorMessage;

    public PersonPopularViewState(State state){
        this.state = state;
    }
    public static PersonPopularViewState progress(){
        PersonPopularViewState state = new PersonPopularViewState(State.NONE);
        state.progress = true;
        return state;
    }

    public static PersonPopularViewState requestSuccess(List<PersonPopularResult> data){
        PersonPopularViewState state = new PersonPopularViewState(State.REQUEST_SUCCESS);
        state.personPopularResults = data;
        return state;
    }

    public static PersonPopularViewState errorMessage(String message){
        PersonPopularViewState state = new PersonPopularViewState(State.ERROR_MESSAGE);
        state.errorMessage = message;
        return state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<PersonPopularResult> getPersonPopularResult() {
        return personPopularResults;
    }

    public void setPersonPopularResult(List<PersonPopularResult> personPopularResult) {
        this.personPopularResults = personPopularResult;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean hasData(){ return personPopularResults != null; }
    public boolean hasErrorMessage(){ return errorMessage != null; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
