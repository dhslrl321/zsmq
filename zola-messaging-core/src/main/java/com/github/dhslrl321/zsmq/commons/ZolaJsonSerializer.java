package com.github.dhslrl321.zsmq.commons;

import com.github.dhslrl321.zsmq.exception.ZolaSerializeException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZolaJsonSerializer {
    // TODO need memory optimization
    private final Gson gson;

    private ZolaJsonSerializer() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .serializeNulls()
                .create();
    }

    private static class SingletonHolder {
        private static final ZolaJsonSerializer instance = new ZolaJsonSerializer();
    }

    public static ZolaJsonSerializer getInstance() {
        return SingletonHolder.instance;
    }

    public String serialize(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            throw new ZolaSerializeException(e.getMessage());
        }
    }

    public <T> T deserialize(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            throw new ZolaSerializeException(e.getMessage());
        }
    }

    private static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }

    private static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_DATE_TIME);
        }
    }
}
