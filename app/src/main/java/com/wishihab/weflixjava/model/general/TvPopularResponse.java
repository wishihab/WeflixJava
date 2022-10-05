package com.wishihab.weflixjava.model.general;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TvPopularResponse implements Serializable {

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private Results[] results;

    public static class Results{

        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("id")
        private String id;
        @SerializedName("original_language")
        private String originalLanguage;
        @SerializedName("original_name")
        private String originalName;
        @SerializedName("overview")
        private String overview;
        @SerializedName("popularity")
        private Double popularity;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("first_air_date")
        private String firstAirDate;
        @SerializedName("vote_average")
        private Double voteAverage;
        @SerializedName("vote_count")
        private Integer voteCount;

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getFirstAirDate() {
            return firstAirDate;
        }

        public void setFirstAirDate(String firstAirDate) {
            this.firstAirDate = firstAirDate;
        }

        public Double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Results[] getResults() {
        return results;
    }

    public void setResults(Results[] results) {
        this.results = results;
    }
}
