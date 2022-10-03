package com.wishihab.weflixjava.apiservice.core;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wishihab.weflixjava.model.core.RawGeoJson;

import java.lang.reflect.Type;
import java.util.Map;


public class RawGeoJsonAdapter implements JsonSerializer<RawGeoJson>, JsonDeserializer<RawGeoJson> {

    @Override
    public RawGeoJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String raw = json.toString();
        Map<String, Object> map = context.deserialize(json, Object.class);
        return new RawGeoJson(raw, map);
    }

    @Override
    public JsonElement serialize(RawGeoJson src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getElements());
    }
}
