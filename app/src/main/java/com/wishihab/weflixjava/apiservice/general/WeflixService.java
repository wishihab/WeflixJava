package com.wishihab.weflixjava.apiservice.general;

import com.wishihab.weflixjava.model.general.MoviePopularResponse;
import com.wishihab.weflixjava.model.general.PersonPopularResponse;
import com.wishihab.weflixjava.model.general.TvPopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeflixService {

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=146f213f7f57825fa9e1e43b7db04884&page=1")
    Call<MoviePopularResponse> getMoviePopularList();

    @GET("https://api.themoviedb.org/3/tv/popular?api_key=146f213f7f57825fa9e1e43b7db04884&page=1")
    Call<TvPopularResponse> getTvPopularList();

    @GET("https://api.themoviedb.org/3/person/popular?api_key=146f213f7f57825fa9e1e43b7db04884&page=1")
    Call<PersonPopularResponse> getPersonPopularList();

}
