package com.github.dhslrl321.zsmq.http;

import com.github.dhslrl321.zsmq.commons.Serializer;
import com.github.dhslrl321.zsmq.core.message.ZolaMessage;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Optional;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;

public class ZolaHttpClient {

    private final Gson gson = new Gson();

    public boolean requestPush(String destination, ZolaMessage message) {
        try {
            Response response = Request.post(destination + "/api/messages")
                    .addHeader("Content-Type", "application/json")
                    .bodyString(gson.toJson(message), ContentType.APPLICATION_JSON)
                    .execute();
            if (response.returnResponse().getCode() == 200) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Optional<ZolaMessage> requestPeek(String destination) {
        try {
            Response response = Request.get(destination + "api/queues/seoul2")
                    .addHeader("Content-Type", "application/json")
                    .execute();
            String json = response.returnContent().asString();
            ZolaMessage message = Serializer.deserialize(json, ZolaMessage.class);
            return Optional.of(message);
            /*if (httpResponse.getCode() == 200) {
                String json = response.returnContent().asString();
                ZolaMessage message = Serializer.deserialize(json, ZolaMessage.class);
                return Optional.of(message);
            } else if (httpResponse.getCode() == 204) {
                return Optional.empty();
            }*/
            // return Optional.empty();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ZolaServerConnectionFailedException("ex");
        }
    }

    public boolean requestAck(String destination) {
        try {
            Response response = Request.delete(destination + "/api/queues/" + destination + "/acknowledge")
                    .addHeader("Content-Type", "application/json")
                    .execute();
            if (response.returnResponse().getCode() == 200) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
