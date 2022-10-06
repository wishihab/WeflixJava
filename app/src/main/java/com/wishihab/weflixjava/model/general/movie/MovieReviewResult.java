package com.wishihab.weflixjava.model.general.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieReviewResult implements Serializable {

    @SerializedName("author")
    private String author;
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;
    @SerializedName("avatar_path")
    private String avatarPath;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("content")
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
