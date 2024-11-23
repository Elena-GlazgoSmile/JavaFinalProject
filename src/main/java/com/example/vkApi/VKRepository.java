package com.example.vkApi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VKRepository {
    private static final String ACCESS_TOKEN = "vk1.a.JjlWEGe8jbvE8M77vYscV7_OjdsQECpajnO7EPClOeCmshnmc51yHKk5lTlxXcHPmPpb5zYOUraX3NRUh96NAvqU8jo-R8xHSKo60bfpvaMankPNGWncHLL1NiFoyx1AUsKtlm6S5zLZ1PyR1UUrqk3dB0pRVxuepnEnAz1f5dFtrsdaeuFstfM0Ib6SgFkDXL49oEgE3ZoYNRdP8alqzg";
    private static final String API_VERSION = "5.131";
    private static final String BASE_URL = "https://api.vk.com/method/";

    private static final OkHttpClient client = new OkHttpClient();


    public String getUserIdByName(String fullName) throws IOException {
        String[] nameParts = fullName.split(" ");
        if (nameParts.length < 2) {
            throw new IllegalArgumentException();
        }
        String firstName = nameParts[0];
        String lastName = nameParts[1];

        String url = BASE_URL + "users.search?q=" + firstName + "%20" + lastName + "&access_token=" + ACCESS_TOKEN + "&v=" + API_VERSION;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException();
            }

            assert response.body() != null;
            String responseBody = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonArray items = jsonObject.getAsJsonObject("response").getAsJsonArray("items");

            if (items.size() > 0) {
                JsonObject user = items.get(0).getAsJsonObject();
                return String.format("ID студента - %s", user.get("id").getAsString());
            } else {
                return "не найден в ВК или имеет страницу с псевдонимом";
            }
        }
    }

    public ArrayList<String> getUserIdsByNames(List<String> fullNames, int from, int to) {
        ArrayList<String> Ids = new ArrayList<>();
        for (String fullName : fullNames.subList(from, to)) {
            try {
                String userId = getUserIdByName(fullName);
                Ids.add(userId);
                TimeUnit.SECONDS.sleep(2);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return Ids;
    }

    public static JsonArray searchUsers(String fullname) throws IOException {
        String url = String.format("https://api.vk.com/method/users.search?q=%s&access_token=%s&v=%s",
                fullname, ACCESS_TOKEN, API_VERSION);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String responseBody = response.body().string();
            JsonObject jsonObj = JsonParser
                    .parseString(responseBody)
                    .getAsJsonObject();
            TimeUnit.SECONDS.sleep(2);
            return jsonObj.getAsJsonObject("response")
                    .getAsJsonArray("items");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonObject getUserCity(int userID) throws IOException {
        String url = String.format("https://api.vk.com/method/users.get?user_ids=%d&fields=city&access_token=%s&v=%s",
                userID, ACCESS_TOKEN, API_VERSION);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client
                .newCall(request)
                .execute()) {
            String responseBody = response.body().string();
            JsonObject jsonObject = JsonParser
                    .parseString(responseBody)
                    .getAsJsonObject();
            TimeUnit.SECONDS.sleep(2);
            return jsonObject.getAsJsonArray("response")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("city");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
