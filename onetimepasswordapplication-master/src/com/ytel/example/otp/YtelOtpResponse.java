package com.ytel.example.otp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class YtelOtpResponse {
  public String otp;
  public JsonArray payload;

  public String getOtp() {
    Gson gson = new Gson();
    JsonObject object = gson.fromJson(payload.get(0), JsonObject.class);
    YtelOtpResponse otpResponse = gson.fromJson(object, YtelOtpResponse.class);
    String otp = otpResponse.otp;
    return otp;
  }

}
