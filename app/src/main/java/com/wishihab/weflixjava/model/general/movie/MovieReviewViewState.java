package com.wishihab.weflixjava.model.general.movie;

import java.util.List;

public class MovieReviewViewState {

    /**
     * The enum State.
     */
    public enum State {
        NONE,
        REQUEST_SUCCESS,
        ERROR_MESSAGE
    }

    private State state;
    private List<MovieReviewResult> movieReviewResults;
    private boolean progress;
    private String errorMessage;

    public MovieReviewViewState(State state){
        this.state = state;
    }
    public static MovieReviewViewState progress(){
        MovieReviewViewState state = new MovieReviewViewState(State.NONE);
        state.progress = true;
        return state;
    }

    public static MovieReviewViewState requestSuccess(List<MovieReviewResult> data){
        MovieReviewViewState state = new MovieReviewViewState(State.REQUEST_SUCCESS);
        state.movieReviewResults = data;
        return state;
    }

    public static MovieReviewViewState errorMessage(String message){
        MovieReviewViewState state = new MovieReviewViewState(State.ERROR_MESSAGE);
        state.errorMessage = message;
        return state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<MovieReviewResult> getMovieReviewResults() {
        return movieReviewResults;
    }

    public void setMovieReviewResults(List<MovieReviewResult> moviePopularResult) {
        this.movieReviewResults = moviePopularResult;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean hasData(){ return movieReviewResults != null; }
    public boolean hasErrorMessage(){ return errorMessage != null; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
