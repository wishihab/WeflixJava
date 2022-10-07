package com.wishihab.weflixjava.model.general.person.detail;


public class PersonDetailViewState {

    /**
     * The enum State.
     */
    public enum State {
        NONE,
        REQUEST_SUCCESS,
        ERROR_MESSAGE
    }

    private State state;
    private PersonDetailResult personDetailResult;
    private boolean progress;
    private String errorMessage;

    public PersonDetailViewState(State state){
        this.state = state;
    }
    public static PersonDetailViewState progress(){
        PersonDetailViewState state = new PersonDetailViewState(State.NONE);
        state.progress = true;
        return state;
    }

    public static PersonDetailViewState requestSuccess(PersonDetailResult data){
        PersonDetailViewState state = new PersonDetailViewState(State.REQUEST_SUCCESS);
        state.personDetailResult = data;
        return state;
    }

    public static PersonDetailViewState errorMessage(String message){
        PersonDetailViewState state = new PersonDetailViewState(State.ERROR_MESSAGE);
        state.errorMessage = message;
        return state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public PersonDetailResult getPersonDetailResult() {
        return personDetailResult;
    }

    public void setPersonDetailResult(PersonDetailResult personDetailResult) {
        this.personDetailResult = personDetailResult;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public boolean hasData(){ return personDetailResult != null; }
    public boolean hasErrorMessage(){ return errorMessage != null; }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
