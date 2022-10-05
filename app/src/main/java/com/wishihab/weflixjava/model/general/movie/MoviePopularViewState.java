package com.wishihab.weflixjava.model.general.movie;

import java.util.List;

public class MoviePopularViewState {

    /**
     * The enum State.
     */
    public enum State {
        NONE,
        REQUEST_SUCCESS,
        ERROR_MESSAGE
    }

    private State state;
    private List<MoviePopularResult> moviePopularResult;
    private boolean progress;
    private String errorMessage;

    public MoviePopularViewState(State state){
        this.state = state;
    }
    public static MoviePopularViewState progress(){
        MoviePopularViewState state = new MoviePopularViewState(State.NONE);
        state.progress = true;
        return state;
    }

    public static MoviePopularViewState requestSuccess(List<MoviePopularResult> data){
        MoviePopularViewState state = new MoviePopularViewState(State.REQUEST_SUCCESS);
        state.moviePopularResult = data;
        return state;
    }

    public static MoviePopularViewState errorMessage(String message){
        MoviePopularViewState state = new MoviePopularViewState(State.ERROR_MESSAGE);
        state.errorMessage = message;
        return state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<MoviePopularResult> getMoviePopularResult() {
        return moviePopularResult;
    }

    public void setMoviePopularResult(List<MoviePopularResult> moviePopularResult) {
        this.moviePopularResult = moviePopularResult;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean hasData(){ return moviePopularResult != null; }
    public boolean hasErrorMessage(){ return errorMessage != null; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
