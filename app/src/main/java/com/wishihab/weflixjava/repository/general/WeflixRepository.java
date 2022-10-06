package com.wishihab.weflixjava.repository.general;

import com.wishihab.weflixjava.model.general.movie.MovieReviewResult;
import com.wishihab.weflixjava.model.general.movie.detail.MovieDetailResponse;
import com.wishihab.weflixjava.model.general.movie.MoviePopularResult;
import com.wishihab.weflixjava.model.general.person.PersonPopularResult;
import com.wishihab.weflixjava.model.general.tv.TvPopularResult;
import com.wishihab.weflixjava.repository.core.ListRepositoryListener;
import com.wishihab.weflixjava.repository.core.RepositoryListener;

public interface WeflixRepository {
    void getMoviePopular(ListRepositoryListener<MoviePopularResult> listener);
    void getMovieDetail(String movieId, RepositoryListener<MovieDetailResponse> listener);
    void getMovieReview(String movieId, ListRepositoryListener<MovieReviewResult> listener);

    void getTvPopular(ListRepositoryListener<TvPopularResult> listener);

    void getPersonPopular(ListRepositoryListener<PersonPopularResult> listener);



}
