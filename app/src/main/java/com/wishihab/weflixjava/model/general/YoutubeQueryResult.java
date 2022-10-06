package com.wishihab.weflixjava.model.general;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.http.GET;

public class YoutubeQueryResult implements Serializable {

    @SerializedName("kind")
    private String kind;
    @SerializedName("videoId")
    private String videoId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
