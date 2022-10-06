package com.wishihab.weflixjava.model.general;

import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;

import java.util.List;

public class YoutubeQueryViewState {

    /**
     * The enum State.
     */
    public enum State {
        NONE,
        REQUEST_SUCCESS,
        ERROR_MESSAGE
    }

    private State state;
    private List<YoutubeQueryResult> youtubeQueryResults;
    private boolean progress;
    private String errorMessage;

    public YoutubeQueryViewState(State state){
        this.state = state;
    }
    public static YoutubeQueryViewState progress(){
        YoutubeQueryViewState state = new YoutubeQueryViewState(State.NONE);
        state.progress = true;
        return state;
    }

    public static YoutubeQueryViewState requestSuccess(List<YoutubeQueryResult> data){
        YoutubeQueryViewState state = new YoutubeQueryViewState(State.REQUEST_SUCCESS);
        state.youtubeQueryResults = data;
        return state;
    }

    public static YoutubeQueryViewState errorMessage(String message){
        YoutubeQueryViewState state = new YoutubeQueryViewState(State.ERROR_MESSAGE);
        state.errorMessage = message;
        return state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<YoutubeQueryResult> getYoutubeQueryResults() {
        return youtubeQueryResults;
    }

    public void setYoutubeQueryResults(List<YoutubeQueryResult> moviePopularResult) {
        this.youtubeQueryResults = moviePopularResult;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean hasData(){ return youtubeQueryResults != null; }
    public boolean hasErrorMessage(){ return errorMessage != null; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
