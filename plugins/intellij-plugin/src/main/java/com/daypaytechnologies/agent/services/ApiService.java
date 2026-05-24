package com.daypaytechnologies.agent.services;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiService {

    private static final String API_KEY =
            "YOUR_OPENAI_KEY";

    private static final String URL =
            "https://api.openai.com/v1/chat/completions";

    private static final OkHttpClient client =
            new OkHttpClient();

    public static String ask(String prompt)
            throws IOException {

        Map<String, Object> body =
                new HashMap<>();

        body.put("model", "gpt-4.1-mini");

        List<Map<String, String>> messages =
                new ArrayList<>();

        Map<String, String> user =
                new HashMap<>();

        user.put("role", "user");
        user.put("content", prompt);

        messages.add(user);

        body.put("messages", messages);

        Gson gson = new Gson();

        String json = gson.toJson(body);

        Request request =
                new Request.Builder()
                        .url(URL)
                        .header(
                                "Authorization",
                                "Bearer " + API_KEY
                        )
                        .post(
                                RequestBody.create(
                                        json,
                                        MediaType.parse(
                                                "application/json"
                                        )
                                )
                        )
                        .build();

        try (Response response =
                     client.newCall(request).execute()) {

            if (!response.isSuccessful()) {

                throw new RuntimeException(
                        response.body().string()
                );
            }

            String result =
                    response.body().string();

            return result;
        }
    }
}
