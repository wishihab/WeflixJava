package com.wishihab.weflixjava.apiservice.core;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wishihab.weflixjava.model.core.DynamicType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DynamicTypeAdapter implements JsonSerializer<DynamicType>, JsonDeserializer<DynamicType> {

    @Override
    public JsonElement serialize(DynamicType src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getValue());
    }

    @Override
    public DynamicType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return toAnyType(json);
    }

    private DynamicType toAnyType(JsonElement json) {

        if (json.isJsonPrimitive()) {
            return fromPrimitive(json);
        }

        if (json.isJsonArray()) {
            return fromArray(json);
        }

        if (json.isJsonObject()) {
            return fromObject(json);
        }

        if (json.isJsonNull()) {
            // null map directly to java null to avoid confusion
            return null;
        }

        throw new JsonParseException("Unhandled type " + json.getClass());
    }

    private DynamicType fromPrimitive(JsonElement json) {
        JsonPrimitive primitive = json.getAsJsonPrimitive();
        if (primitive.isString()) {
            return new DynamicType.StringType(primitive.getAsString());
        }
        if (primitive.isBoolean()) {
            return new DynamicType.BooleanType(primitive.getAsBoolean());
        }
        if (primitive.isNumber()) {
            return new DynamicType.NumberType(primitive.getAsNumber());
        }
        throw new JsonParseException("Unhandled type " + primitive.getClass());
    }

    private DynamicType fromObject(JsonElement json) {
        JsonObject object = json.getAsJsonObject();
        Map<String, DynamicType> map = new LinkedHashMap<>();

        for (String key : object.keySet()) {
            DynamicType value = toAnyType(object.get(key));
            map.put(key, value);
        }
        return new DynamicType.MapType(map);
    }

    private DynamicType fromArray(JsonElement json) {
        JsonArray array = json.getAsJsonArray();
        int n = array.size();
        List<DynamicType> list = new ArrayList<>();
        for (int i= 0; i<n; i++) {
            list.add(toAnyType(array.get(i)));
        }
        return new DynamicType.ListType(list);
    }

}
