package com.ytel.example.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetYtelAuthorizationToken {

  public static AuthTokenResponse getToken(String username, String password) throws IOException {

    URL url = new URL("https://api-beta.ytel.com/auth/v2/token/");
    HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();

    postConnection.setRequestMethod("POST");
    postConnection.setRequestProperty("accept", "application/json");
    postConnection.setRequestProperty("Content-Type", "application/json");

    postConnection.setDoOutput(true);

    AuthTokenRequest request = new AuthTokenRequest(username, password);

    Gson gson = new GsonBuilder().create();
    String getToken = gson.toJson(request).toString();

    try (OutputStream os = postConnection.getOutputStream()) {
      byte[] input = getToken.getBytes("utf-8");
      os.write(input, 0, input.length);
    }
    int responseCode = postConnection.getResponseCode();

    if ((responseCode == HttpURLConnection.HTTP_CREATED) || (responseCode == HttpURLConnection.HTTP_OK)) {
      BufferedReader in = new BufferedReader(
          new InputStreamReader(postConnection.getInputStream(), "utf-8"));
      StringBuilder content = new StringBuilder();
      String inputLine = null;

      while (null != (inputLine = in.readLine())) {
        content.append(inputLine.trim());
      }
      in.close();
      JsonObject jsonObject = new JsonParser().parse(content.toString()).getAsJsonObject();
      AuthTokenResponse authToken = gson.fromJson(jsonObject, AuthTokenResponse.class);
      postConnection.disconnect();
      return authToken;
    } else {
      return null;
    }
  }
}
