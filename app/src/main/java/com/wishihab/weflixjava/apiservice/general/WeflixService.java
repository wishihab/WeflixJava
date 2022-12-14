package com.wishihab.weflixjava.apiservice.general;

import com.wishihab.weflixjava.model.general.YoutubeQueryResponse;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailResponse;
import com.wishihab.weflixjava.model.general.movie.MovieReviewResponse;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResponse;
import com.wishihab.weflixjava.model.general.person.PersonPopularResponse;
import com.wishihab.weflixjava.model.general.person.detail.PersonDetailResult;
import com.wishihab.weflixjava.model.general.tv.TvPopularResponse;
import com.wishihab.weflixjava.model.general.tv.detail.TvDetailResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeflixService {

    //movie
    @GET("movie/popular?api_key=146f213f7f57825fa9e1e43b7db04884&")
    Call<MoviePopularResponse> getMoviePopularList(
            @Query("page") Integer page
    );

    @GET("movie/{movie_id}?api_key=146f213f7f57825fa9e1e43b7db04884")
    Call<MovieDetailResponse> getDetailMovie(
            @Path("movie_id") String movieId
    );

    @GET("movie/{movie_id}/reviews?api_key=146f213f7f57825fa9e1e43b7db04884")
    Call<MovieReviewResponse> getMovieReview(
            @Path("movie_id") String movieId
    );

    @GET("https://youtube.googleapis.com/youtube/v3/search")
    Call<YoutubeQueryResponse> getYoutubeVideo(
            @Query("q") String movieTitle,
            @Query("key") String apiKey
    );

    //tv
    @GET("tv/popular?api_key=146f213f7f57825fa9e1e43b7db04884&page=1")
    Call<TvPopularResponse> getTvPopularList();

    @GET("tv/{tvId}?api_key=146f213f7f57825fa9e1e43b7db04884")
    Call<TvDetailResult> getDetailTv(
            @Path("tvId") String tvId
    );

    //person
    @GET("person/popular?api_key=146f213f7f57825fa9e1e43b7db04884&page=1")
    Call<PersonPopularResponse> getPersonPopularList();

    @GET("person/{personId}?api_key=146f213f7f57825fa9e1e43b7db04884")
    Call<PersonDetailResult> getDetailPerson(
            @Path("personId") String personId
    );


}
