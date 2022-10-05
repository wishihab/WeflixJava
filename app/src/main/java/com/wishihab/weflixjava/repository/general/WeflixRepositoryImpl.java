package com.wishihab.weflixjava.repository.general;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.wishihab.weflixjava.apiservice.core.ErrorResponseDecoder;
import com.wishihab.weflixjava.apiservice.core.SimpleCallback;
import com.wishihab.weflixjava.apiservice.general.WeflixNetworkServiceFactory;
import com.wishihab.weflixjava.apiservice.general.WeflixService;
import com.wishihab.weflixjava.model.general.MovieDetailResponse;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResponse;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.model.general.person.PersonPopularResponse;
import com.wishihab.weflixjava.model.general.person.PersonPopularResult;
import com.wishihab.weflixjava.model.general.tv.TvPopularResponse;
import com.wishihab.weflixjava.model.general.tv.TvPopularResult;
import com.wishihab.weflixjava.repository.core.ListRepositoryListener;
import com.wishihab.weflixjava.repository.core.RepositoryListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class WeflixRepositoryImpl implements WeflixRepository {
    private final Application application;
    private final ErrorResponseDecoder responseDecoder;

    public WeflixRepositoryImpl(Application application){
        this.application = application;
        responseDecoder = new ErrorResponseDecoder(application, Collections.emptyMap());
    }

    @Override
    public void getMoviePopular(ListRepositoryListener<MoviePopularResult> listener) {
        createNetworkService().getMoviePopularList().enqueue(new SimpleCallback<MoviePopularResponse>() {
            @Override
            protected void onHttpResponseSuccess(Call<MoviePopularResponse> call, Response<MoviePopularResponse> response) {
                MoviePopularResponse body = response.body();
                List<MoviePopularResult> list = insertMovie(body);
                listener.onSuccess(list);
            }

            @Override
            protected void onHttpResponseFailed(Call<MoviePopularResponse> call, Response<MoviePopularResponse> response) {
                listener.onError(responseDecoder.getErrorMessage(response));
            }

            @Override
            public void onFailure(Call<MoviePopularResponse> call, Throwable t) {
                listener.onError(responseDecoder.getMessageFromRetrofitException(t));
            }
        });
    }


    @NonNull
    private List<MoviePopularResult> insertMovie(MoviePopularResponse body){
        MoviePopularResponse.Results[] data  = body.getResults();
        List<MoviePopularResult> list = new ArrayList<>();
        for(MoviePopularResponse.Results item : data){
            MoviePopularResult movie = new MoviePopularResult();
            movie.setAdult(item.isAdult());
            movie.setBackdropPath(item.getBackdropPath());
            movie.setId(item.getId());
            movie.setOriginalLanguage(item.getOriginalLanguage());
            movie.setOriginalTitle(item.getOriginalTitle());
            movie.setOverview(item.getOverview());
            movie.setPopularity(item.getPopularity());
            String fullPosterPath = "https://image.tmdb.org/t/p/w500/" + item.getPosterPath();
            movie.setPosterPath(fullPosterPath);
            movie.setReleaseDate(item.getReleaseDate());
            movie.setTitle(item.getTitle());
            movie.setVoteAverage(item.getVoteAverage());
            movie.setVoteCount(item.getVoteCount());
            list.add(movie);
        }
        return list;
    }

    @Override
    public void getTvPopular(ListRepositoryListener<TvPopularResult> listener) {
        createNetworkService().getTvPopularList().enqueue(new SimpleCallback<TvPopularResponse>() {
            @Override
            protected void onHttpResponseSuccess(Call<TvPopularResponse> call, Response<TvPopularResponse> response) {
                TvPopularResponse body = response.body();
                List<TvPopularResult> list = insertTv(body);
                listener.onSuccess(list);
            }

            @Override
            protected void onHttpResponseFailed(Call<TvPopularResponse> call, Response<TvPopularResponse> response) {
                listener.onError(responseDecoder.getErrorMessage(response));
            }

            @Override
            public void onFailure(Call<TvPopularResponse> call, Throwable t) {
                listener.onError(responseDecoder.getMessageFromRetrofitException(t));
            }
        });
    }


    @NonNull
    private List<TvPopularResult> insertTv(TvPopularResponse body){
        TvPopularResponse.Results[] data  = body.getResults();
        List<TvPopularResult> list = new ArrayList<>();
        for(TvPopularResponse.Results item : data){
            TvPopularResult movie = new TvPopularResult();
            movie.setBackdropPath(item.getBackdropPath());
            movie.setId(item.getId());
            movie.setOriginalLanguage(item.getOriginalLanguage());
            movie.setOriginalName(item.getOriginalName());
            movie.setOverview(item.getOverview());
            movie.setPopularity(item.getPopularity());
            String fullPosterPath = "https://image.tmdb.org/t/p/w500/" + item.getPosterPath();
            movie.setPosterPath(fullPosterPath);
            movie.setFirstAirDate(item.getFirstAirDate());
            movie.setVoteAverage(item.getVoteAverage());
            movie.setVoteCount(item.getVoteCount());
            list.add(movie);
        }
        return list;
    }

    @Override
    public void getPersonPopular(ListRepositoryListener<PersonPopularResult> listener) {
        createNetworkService().getPersonPopularList().enqueue(new SimpleCallback<PersonPopularResponse>() {
            @Override
            protected void onHttpResponseSuccess(Call<PersonPopularResponse> call, Response<PersonPopularResponse> response) {
                PersonPopularResponse body = response.body();
                List<PersonPopularResult> list = insertPerson(body);
                listener.onSuccess(list);
            }

            @Override
            protected void onHttpResponseFailed(Call<PersonPopularResponse> call, Response<PersonPopularResponse> response) {
                listener.onError(responseDecoder.getErrorMessage(response));
            }

            @Override
            public void onFailure(Call<PersonPopularResponse> call, Throwable t) {
                listener.onError(responseDecoder.getMessageFromRetrofitException(t));
            }
        });
    }

    @Override
    public void getMovieDetail(String movieId, RepositoryListener<MovieDetailResponse> listener) {
        createNetworkService().getDetailMovie(movieId).enqueue(new SimpleCallback<MovieDetailResponse>() {
            @Override
            protected void onHttpResponseSuccess(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                listener.onSuccess(response.body());
            }

            @Override
            protected void onHttpResponseFailed(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                listener.onError(responseDecoder.getErrorMessage(response));
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                Log.e("failure", " failure" + t.getMessage());
                listener.onError(responseDecoder.getMessageFromRetrofitException(t));
            }
        });
    }

    @NonNull
    private List<PersonPopularResult> insertPerson(PersonPopularResponse body){
        PersonPopularResponse.Results[] data  = body.getResults();
        List<PersonPopularResult> list = new ArrayList<>();
        for(PersonPopularResponse.Results item : data){
            PersonPopularResult person = new PersonPopularResult();
            person.setAdult(item.getAdult());
            person.setGender(item.getGender());
            person.setId(item.getId());
            person.setKnowForDepartment(item.getKnowForDepartment());
            person.setName(item.getName());
            person.setPopularity(item.getPopularity());
            String fullProfile = "https://image.tmdb.org/t/p/w500/" + item.getProfilePath();
            person.setProfilePath(fullProfile);
            list.add(person);
        }
        return list;
    }

    private WeflixService createNetworkService(){
        return WeflixNetworkServiceFactory.createDefaultServiceNoToken(application, WeflixService.class);
    }
}
