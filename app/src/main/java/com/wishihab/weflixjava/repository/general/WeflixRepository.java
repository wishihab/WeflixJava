package com.wishihab.weflixjava.repository.general;

import com.wishihab.weflixjava.model.general.MoviePopularResult;
import com.wishihab.weflixjava.repository.core.ListRepositoryListener;
import com.wishihab.weflixjava.repository.core.RepositoryListener;

public interface WeflixRepository {
    void getMoviePopular(ListRepositoryListener<MoviePopularResult> listener);

}
