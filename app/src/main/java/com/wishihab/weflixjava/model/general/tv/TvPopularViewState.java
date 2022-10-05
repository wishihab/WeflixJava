package com.wishihab.weflixjava.model.general.tv;

import java.util.List;

public class TvPopularViewState {

    /**
     * The enum State.
     */
    public enum State {
        NONE,
        REQUEST_SUCCESS,
        ERROR_MESSAGE
    }

    private State state;
    private List<TvPopularResult> tvPopularResults;
    private boolean progress;
    private String errorMessage;

    public TvPopularViewState(State state){
        this.state = state;
    }
    public static TvPopularViewState progress(){
        TvPopularViewState state = new TvPopularViewState(State.NONE);
        state.progress = true;
        return state;
    }

    public static TvPopularViewState requestSuccess(List<TvPopularResult> data){
        TvPopularViewState state = new TvPopularViewState(State.REQUEST_SUCCESS);
        state.tvPopularResults = data;
        return state;
    }

    public static TvPopularViewState errorMessage(String message){
        TvPopularViewState state = new TvPopularViewState(State.ERROR_MESSAGE);
        state.errorMessage = message;
        return state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<TvPopularResult> getTvPopularResult() {
        return tvPopularResults;
    }

    public void setTvPopularResult(List<TvPopularResult> tvPopularResults) {
        this.tvPopularResults = tvPopularResults;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean hasData(){ return tvPopularResults != null; }
    public boolean hasErrorMessage(){ return errorMessage != null; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
