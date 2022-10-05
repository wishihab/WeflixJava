package com.wishihab.weflixjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wishihab.weflixjava.model.general.MoviePopularResult;
import com.wishihab.weflixjava.model.general.MoviePopularViewState;
import com.wishihab.weflixjava.repository.core.ListRepositoryListener;
import com.wishihab.weflixjava.repository.general.WeflixRepository;
import com.wishihab.weflixjava.repository.general.WeflixRepositoryImpl;

import java.util.List;

public class WeflixViewModelImpl extends AndroidViewModel implements WeflixViewModel {

    private final MutableLiveData<MoviePopularViewState> movieViewState;
    private final WeflixRepository weflixRepository;

    public WeflixViewModelImpl(Application application){
        this(application, new WeflixRepositoryImpl(application));
    }

    public WeflixViewModelImpl(@NonNull Application application, WeflixRepository weflixRepository){
        super(application);
        this.weflixRepository = weflixRepository;
        movieViewState = new MutableLiveData<>();
    }
    @Override
    public LiveData<MoviePopularViewState> getMovieViewState() {
        if(movieViewState.getValue() == null){
            refreshMovie();
        }
        return movieViewState;
    }

    @Override
    public void refreshMovie() {
        movieViewState.postValue(MoviePopularViewState.progress());
        weflixRepository.getMoviePopular(new ListRepositoryListener<MoviePopularResult>(){

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
}
