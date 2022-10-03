package com.wishihab.weflixjava.model.core;

import java.io.Serializable;
import java.util.Map;

/** Raw geojson object.
 *
 */
public final class RawGeoJson implements Serializable {

    private final String geoJson;
    private final Map<String,Object> elements;

    public RawGeoJson() {
        geoJson = null;
        elements = null;
    }

    public RawGeoJson(String geoJson, Map<String, Object> elements) {
        this.geoJson = geoJson;
        this.elements = elements;
    }

    public String getGeoJson() {
        return geoJson;
    }

    public Map<String, Object> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "RawGeoJson{" +
               "geoJson='" + geoJson + '\'' +
               ", elements=" + elements +
               '}';
    }
}
