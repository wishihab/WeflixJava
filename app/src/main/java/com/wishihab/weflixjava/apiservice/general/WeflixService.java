package com.wishihab.weflixjava.apiservice.general;

import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailResponse;
import com.wishihab.weflixjava.model.general.movie.MovieReviewResponse;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResponse;
import com.wishihab.weflixjava.model.general.person.PersonPopularResponse;
import com.wishihab.weflixjava.model.general.tv.TvPopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeflixService {

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=146f213f7f57825fa9e1e43b7db04884&page=1")
    Call<MoviePopularResponse> getMoviePopularList();

    @GET("https://api.themoviedb.org/3/tv/popular?api_key=146f213f7f57825fa9e1e43b7db04884&page=1")
    Call<TvPopularResponse> getTvPopularList();

    @GET("https://api.themoviedb.org/3/person/popular?api_key=146f213f7f57825fa9e1e43b7db04884&page=1")
    Call<PersonPopularResponse> getPersonPopularList();

    @GET("https://api.themoviedb.org/3/movie/{movie_id}?api_key=146f213f7f57825fa9e1e43b7db04884")
    Call<MovieDetailResponse> getDetailMovie(
            @Path("movie_id") String movieId
    );

    @GET("https://api.themoviedb.org/3/movie/{movie_id}/reviews?api_key=146f213f7f57825fa9e1e43b7db04884")
    Call<MovieReviewResponse> getMovieReview(
            @Path("movie_id") String movieId
    );


}
