package com.wishihab.weflixjava.model.general;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PersonPopularResponse implements Serializable {

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private Results[] results;

    public static class Results{
        @SerializedName("adult")
        private Boolean adult;
        @SerializedName("id")
        private String id;
        @SerializedName("gender")
        private Integer gender;
        @SerializedName("known_for_department")
        private String knowForDepartment;
        @SerializedName("name")
        private String name;
        @SerializedName("popularity")
        private Double popularity;
        @SerializedName("profile_path")
        private String profilePath;

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKnowForDepartment() {
            return knowForDepartment;
        }

        public void setKnowForDepartment(String knowForDepartment) {
            this.knowForDepartment = knowForDepartment;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
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
