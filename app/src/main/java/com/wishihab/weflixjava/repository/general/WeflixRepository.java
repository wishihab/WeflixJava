package com.wishihab.weflixjava.repository.general;

import com.wishihab.weflixjava.model.general.MoviePopularResult;
import com.wishihab.weflixjava.model.general.TvPopularResult;
import com.wishihab.weflixjava.repository.core.ListRepositoryListener;

public interface WeflixRepository {
    void getMoviePopular(ListRepositoryListener<MoviePopularResult> listener);

    void getTvPopular(ListRepositoryListener<TvPopularResult> listener);
}
