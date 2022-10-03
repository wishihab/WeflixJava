package com.wishihab.weflixjava.apiservice.core;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wishihab.weflixjava.model.core.DynamicType;
import com.wishihab.weflixjava.model.core.RawGeoJson;

public class GsonFactory {

    public static Gson createGson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(RawGeoJson.class, new RawGeoJsonAdapter())
                .registerTypeAdapter(DynamicType.class, new DynamicTypeAdapter())
                .create();
        return gson;
    }
}
