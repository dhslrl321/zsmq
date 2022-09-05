package com.github.dhslrl321.zsmq.conn;

import com.github.dhslrl321.zsmq.message.ZolaMessage;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;

public class ZolaHttpClient {

    private final Gson gson = new Gson();

    public boolean post(String destination, ZolaMessage message) {
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
}
