package com.wishihab.weflixjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wishihab.weflixjava.model.general.YoutubeQueryResult;
import com.wishihab.weflixjava.model.general.YoutubeQueryViewState;
import com.wishihab.weflixjava.model.general.movie.MovieReviewResult;
import com.wishihab.weflixjava.model.general.movie.MovieReviewViewState;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailResponse;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailViewState;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.model.general.movie.MoviePopularViewState;
import com.wishihab.weflixjava.model.general.person.PersonPopularResult;
import com.wishihab.weflixjava.model.general.person.PersonPopularViewState;
import com.wishihab.weflixjava.model.general.person.detail.PersonDetailResult;
import com.wishihab.weflixjava.model.general.person.detail.PersonDetailViewState;
import com.wishihab.weflixjava.model.general.tv.TvPopularResult;
import com.wishihab.weflixjava.model.general.tv.TvPopularViewState;
import com.wishihab.weflixjava.repository.core.ListRepositoryListener;
import com.wishihab.weflixjava.repository.core.RepositoryListener;
import com.wishihab.weflixjava.repository.general.WeflixRepository;
import com.wishihab.weflixjava.repository.general.WeflixRepositoryImpl;

import java.util.List;

public class WeflixViewModelImpl extends AndroidViewModel implements WeflixViewModel {

    private final MutableLiveData<MoviePopularViewState> movieViewState;
    private final MutableLiveData<TvPopularViewState> tvViewState;
    private final MutableLiveData<PersonPopularViewState> personViewState;
    private final MutableLiveData<MovieDetailViewState> movieDetailViewState;
    private final MutableLiveData<MovieReviewViewState> movieReviewViewState;
    private final MutableLiveData<YoutubeQueryViewState> youtubeIdViewState;
    private final MutableLiveData<PersonDetailViewState> personDetailViewState;
    private final WeflixRepository weflixRepository;

    public WeflixViewModelImpl(Application application){
        this(application, new WeflixRepositoryImpl(application));
    }

    public WeflixViewModelImpl(@NonNull Application application, WeflixRepository weflixRepository){
        super(application);
        this.weflixRepository = weflixRepository;
        movieViewState = new MutableLiveData<>();
        tvViewState = new MutableLiveData<>();
        personViewState = new MutableLiveData<>();
        movieDetailViewState = new MutableLiveData<>();
        movieReviewViewState = new MutableLiveData<>();
        youtubeIdViewState = new MutableLiveData<>();
        personDetailViewState = new MutableLiveData<>();
    }
    @Override
    public LiveData<MoviePopularViewState> getMovieViewState() {
        if(movieViewState.getValue() == null){
            refreshMovie();
        }
        return movieViewState;
    }

    @Override
    public void doGetMoviePage(Integer page) {
        movieViewState.postValue(MoviePopularViewState.progress());
        weflixRepository.getMoviePopular(page, new ListRepositoryListener<MoviePopularResult>(){

            @Override
            public void onSuccess(@NonNull List<MoviePopularResult> data) {
                movieViewState.postValue(MoviePopularViewState.requestSuccess(data));
            }

            @Override
            public void onError(@NonNull String message) {
                movieViewState.postValue(MoviePopularViewState.errorMessage(message));
            }
        });
    }

    @Override
    public LiveData<TvPopularViewState> getTvViewState() {
        if(tvViewState.getValue() == null){
            refreshTv();
        }
        return tvViewState;
    }

    @Override
    public LiveData<PersonPopularViewState> getPersonViewState() {
        if(personViewState.getValue() == null){
            refreshPerson();
        }
        return personViewState;
    }

    @Override
    public LiveData<PersonDetailViewState> getPersonDetailViewstate() {
        return personDetailViewState;
    }

    @Override
    public void doGetPersonDetail(String personId) {
        personDetailViewState.postValue(PersonDetailViewState.progress());
        weflixRepository.getPersonDetail(personId, new RepositoryListener<PersonDetailResult>() {
            @Override
            public void onSuccess(@NonNull PersonDetailResult data) {
                personDetailViewState.postValue(PersonDetailViewState.requestSuccess(data));
            }

            @Override
            public void onError(@NonNull String message) {
                personDetailViewState.postValue(PersonDetailViewState.errorMessage(message));
            }
        });
    }

    @Override
    public void refresh() {
        refreshMovie();
        refreshTv();
        refreshPerson();
    }

    @Override
    public void refreshMovie() {

    }

    @Override
    public void refreshTv() {
        tvViewState.postValue(TvPopularViewState.progress());
        weflixRepository.getTvPopular(new ListRepositoryListener<TvPopularResult>(){

            @Override
            public void onSuccess(@NonNull List<TvPopularResult> data) {
                tvViewState.postValue(TvPopularViewState.requestSuccess(data));
            }

            @Override
            public void onError(@NonNull String message) {
                tvViewState.postValue(TvPopularViewState.errorMessage(message));
            }
        });
    }

    @Override
    public void refreshPerson() {
        personViewState.postValue(PersonPopularViewState.progress());
        weflixRepository.getPersonPopular(new ListRepositoryListener<PersonPopularResult>(){

            @Override
            public void onSuccess(@NonNull List<PersonPopularResult> data) {
                personViewState.postValue(PersonPopularViewState.requestSuccess(data));
            }

            @Override
            public void onError(@NonNull String message) {
                personViewState.postValue(PersonPopularViewState.errorMessage(message));
            }
        });
    }

    @Override
    public LiveData<MovieDetailViewState> getMovieDetailViewState() {
        return movieDetailViewState;
    }

    @Override
    public LiveData<MovieReviewViewState> getMovieReviewViewState() {
        return movieReviewViewState;
    }

    @Override
    public void doGetMovieDetail(String movieId) {
        movieDetailViewState.postValue(MovieDetailViewState.progress());
        weflixRepository.getMovieDetail(movieId, new RepositoryListener<MovieDetailResponse>() {
            @Override
            public void onSuccess(@NonNull MovieDetailResponse data) {
                movieDetailViewState.postValue(MovieDetailViewState.requestSuccess(data));
            }

            @Override
            public void onError(@NonNull String message) {
                movieDetailViewState.postValue(MovieDetailViewState.errorMessage(message));
            }
        });
    }

    @Override
    public void doGetMovieReview(String movieId) {
        movieReviewViewState.postValue(MovieReviewViewState.progress());
        weflixRepository.getMovieReview(movieId, new ListRepositoryListener<MovieReviewResult>() {
            @Override
            public void onSuccess(@NonNull List<MovieReviewResult> data) {
                movieReviewViewState.postValue(MovieReviewViewState.requestSuccess(data));
            }

            @Override
            public void onError(@NonNull String message) {
                movieReviewViewState.postValue(MovieReviewViewState.errorMessage(message));
            }
        });
    }

    @Override
    public LiveData<YoutubeQueryViewState> getYoutubeIdViewState() {
        return youtubeIdViewState;
    }

    @Override
    public void doGetYoutubeId(String movieName, String apiKey) {
        youtubeIdViewState.postValue(YoutubeQueryViewState.progress());
        weflixRepository.getYoutubeVideo(movieName, apiKey, new ListRepositoryListener<YoutubeQueryResult>() {
            @Override
            public void onSuccess(@NonNull List<YoutubeQueryResult> data) {
                youtubeIdViewState.postValue(YoutubeQueryViewState.requestSuccess(data));
            }

            @Override
            public void onError(@NonNull String message) {
                youtubeIdViewState.postValue(YoutubeQueryViewState.errorMessage(message));
            }
        });
    }

}
