package com.github.dhslrl321.zsmq.commons;

import com.github.dhslrl321.zsmq.core.message.MediaTypes;
import com.github.dhslrl321.zsmq.core.message.ZolaHeader;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.github.dhslrl321.zsmq.core.message.ZolaPayload;
import com.github.dhslrl321.zsmq.core.queue.QueueName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Serializer2 {
    // TODO need memory optimization
    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .serializeNulls()
                .create();
    }

    public static String serialize(Object object) {
        return gson.toJson(object);
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    private static class ZolaHeaderSerializer implements JsonSerializer<ZolaHeader> {

        @Override
        public JsonElement serialize(ZolaHeader src, Type typeOfSrc, JsonSerializationContext context) {
            /*String queueName = gson.toJson(src.getQueueName().getValue());
            String timestamp = gson.toJson(src.getTimestamp());
            String mediaType = gson.toJson(src.getMediaType());*/

            String queueName = Serializer2.serialize(src.getQueueName().getValue());
            String timestamp = Serializer2.serialize(src.getTimestamp());
            //String mediaType = Serializer.serialize(src.getMediaType());

            JsonObject json = new JsonObject();
            json.addProperty("queueName", queueName);
            json.addProperty("timestamp", timestamp);
            json.addProperty("mediaType", src.getMediaType().toString());
            return json;
        }
    }
    private static class ZolaHeaderDeserializer implements JsonDeserializer<ZolaHeader> {
        @Override
        public ZolaHeader deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement queueNameElement = jsonObject.get("queueName");
            LocalDateTime timestamp = gson.fromJson(jsonObject.get("timestamp"), LocalDateTime.class);
            JsonElement mediaTypeElement = jsonObject.get("mediaType");
            return ZolaHeader.of(QueueName.of(queueNameElement.getAsString()), timestamp, MediaTypes.valueOf(mediaTypeElement.getAsString()));
        }
    }

    private static class ZolaPayloadSerializer implements JsonSerializer<ZolaPayload> {
        @Override
        public JsonElement serialize(ZolaPayload src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            String payloadValue = gson.toJson(src.getValue());

            jsonObject.addProperty("payload", payloadValue);
            return jsonObject;
        }
    }

    private static class ZolaMessageDeserializer implements JsonDeserializer<ZolaMessage> {

        @Override
        public ZolaMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            ZolaHeader header = gson.fromJson(jsonObject.get("header"), ZolaHeader.class);
            ZolaPayload payload = gson.fromJson(jsonObject.get("payload"), ZolaPayload.class);
            return ZolaMessage.of(header, payload);
        }
    }

    private static class ZolaMessageSerializer implements JsonSerializer<ZolaMessage> {

        @Override
        public JsonElement serialize(ZolaMessage src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            ZolaHeader zolaHeader = src.getHeader();
            ZolaPayload zolaPayload = src.getPayload();

            String header = gson.toJson(zolaHeader);
            String payload = gson.toJson(zolaPayload);
            jsonObject.addProperty("header", header);
            jsonObject.addProperty("payload", payload);
            return jsonObject;
        }
    }

    private static class ZolaPayloadDeserializer implements JsonDeserializer<ZolaPayload> {

        @Override
        public ZolaPayload deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return ZolaPayload.of(json.getAsString());
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
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_DATE_TIME);
        }
    }
}
