package com.wishihab.weflixjava.model.core;


import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * Map location in latitude and longitude.
 * latitude and longitude of this class may be null.
 */
public class LatLong implements Serializable {
    private Double latitude;
    private Double longitude;

    public LatLong(@Nullable Double latitude, @Nullable Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLong() {
    }

    public LatLong(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Nullable
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Nullable
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    @NonNull
    public String toString() {
        return "GpsLocation{" +
               "latitude=" + latitude +
               ", longitude=" + longitude +
               '}';
    }

    // generated code

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LatLong location = (LatLong) o;

        if (latitude != null ? !latitude.equals(location.latitude) : location.latitude != null)
            return false;
        return longitude != null ? longitude.equals(location.longitude)
                                 : location.longitude == null;

    }

    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
