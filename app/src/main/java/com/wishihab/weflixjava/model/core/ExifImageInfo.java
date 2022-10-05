package com.wishihab.weflixjava.model.core;

import android.net.Uri;

import java.util.Date;

public class ExifImageInfo {

    /**
     * Local image uri.
     */
    private Uri uri;

    /**
     * image location.
     */
    private LatLong location;

    /**
     * image created date (when image captured).
     * It is in the ORIGINAL exif date format.
     */
    private Date createdDate;

    /**
     * Rotation, in degree if available.
     */
    private Integer rotation;

    // may contains other exif data if required

    public ExifImageInfo() {
    }

    public ExifImageInfo(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public LatLong getLocation() {
        return location;
    }

    public void setLocation(LatLong location) {
        this.location = location;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }
}
