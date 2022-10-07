package com.wishihab.weflixjava.model.general.tv.detail;


import com.wishihab.weflixjava.model.general.person.detail.PersonDetailResult;

public class TvDetailViewState {

    /**
     * The enum State.
     */
    public enum State {
        NONE,
        REQUEST_SUCCESS,
        ERROR_MESSAGE
    }

    private State state;
    private TvDetailResult tvDetailResult;
    private boolean progress;
    private String errorMessage;

    public TvDetailViewState(State state){
        this.state = state;
    }
    public static TvDetailViewState progress(){
        TvDetailViewState state = new TvDetailViewState(State.NONE);
        state.progress = true;
        return state;
    }

    public static TvDetailViewState requestSuccess(TvDetailResult data){
        TvDetailViewState state = new TvDetailViewState(State.REQUEST_SUCCESS);
        state.tvDetailResult = data;
        return state;
    }

    public static TvDetailViewState errorMessage(String message){
        TvDetailViewState state = new TvDetailViewState(State.ERROR_MESSAGE);
        state.errorMessage = message;
        return state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public TvDetailResult getTvDetailResult() {
        return tvDetailResult;
    }

    public void setTvDetailResult(TvDetailResult tvDetailResult) {
        this.tvDetailResult = tvDetailResult;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean hasData(){ return tvDetailResult != null; }
    public boolean hasErrorMessage(){ return errorMessage != null; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
