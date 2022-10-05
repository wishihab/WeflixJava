package com.wishihab.weflixjava.model.general;

import java.util.List;

public class MovieDetailViewState {

    /**
     * The enum State.
     */
    public enum State {
        NONE,
        REQUEST_SUCCESS,
        ERROR_MESSAGE
    }

    private State state;
    private MovieDetailResponse movieDetailResponse;
    private boolean progress;
    private String errorMessage;

    public MovieDetailViewState(State state){
        this.state = state;
    }
    public static MovieDetailViewState progress(){
        MovieDetailViewState state = new MovieDetailViewState(State.NONE);
        state.progress = true;
        return state;
    }

    public static MovieDetailViewState requestSuccess(MovieDetailResponse data){
        MovieDetailViewState state = new MovieDetailViewState(State.REQUEST_SUCCESS);
        state.movieDetailResponse = data;
        return state;
    }

    public static MovieDetailViewState errorMessage(String message){
        MovieDetailViewState state = new MovieDetailViewState(State.ERROR_MESSAGE);
        state.errorMessage = message;
        return state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public MovieDetailResponse getMovieDetailResponse() {
        return movieDetailResponse;
    }

    public void setMovieDetailResponse(MovieDetailResponse movieDetailResponse) {
        this.movieDetailResponse = movieDetailResponse;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean hasData(){ return movieDetailResponse != null; }
    public boolean hasErrorMessage(){ return errorMessage != null; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
