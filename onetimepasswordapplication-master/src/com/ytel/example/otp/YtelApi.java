package com.ytel.example.otp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.ytel.example.otp.ResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class YtelApi {
  @SuppressWarnings("serial")
  private static class WrappedYtelOtpResponse extends ResponseWrapper<YtelOtpResponse> {
  }

  public static YtelOtpResponse sendOtp(String toNumber, String accessToken) throws IOException {

    URL url = new URL("https://api-beta.ytel.com/api/v4/sms/otp");
    HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();

    postConnection.setRequestMethod("POST");
    postConnection.setRequestProperty("accept", "application/json");
    postConnection.setRequestProperty("Content-Type", "application/json");
    postConnection.setRequestProperty("Authorization", "Bearer " + accessToken);

    postConnection.setDoOutput(true);

    OtpRequest request = new OtpRequest(toNumber);

    Gson gson = new GsonBuilder().create();
    String getToken = gson.toJson(request).toString();

    try (OutputStream os = postConnection.getOutputStream()) {
      byte[] input = getToken.getBytes("utf-8");
      os.write(input, 0, input.length);
    }
    int responseCode = postConnection.getResponseCode();

    if ((responseCode == HttpURLConnection.HTTP_CREATED) || (responseCode == HttpURLConnection.HTTP_OK)) {
      BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream(), "utf-8"));
      StringBuilder content = new StringBuilder();
      String inputLine = null;
      while (null != (inputLine = in.readLine())) {
        content.append(inputLine.trim());
      }
      in.close();
      YtelOtpResponse otpResponse = gson.fromJson(content.toString(), YtelOtpResponse.class);
      postConnection.disconnect();
      return otpResponse;
    } else {
      postConnection.getErrorStream();
      return null;
    }

  }

}
