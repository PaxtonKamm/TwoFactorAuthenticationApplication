package com.ytel.example;

import com.ytel.example.auth.AuthTokenResponse;
import com.ytel.example.auth.GetYtelAuthorizationToken;
import com.ytel.example.otp.YtelApi;
import com.ytel.example.otp.YtelOtpResponse;
import java.io.IOException;

public class OneTimePasswordApplication {

  public static void main(String[] args) throws IOException {

    if (args.length != 3) {
      System.out.println("Usage: APP username password toNumber");
    }

    String userName = args[0];
    String password = args[1];
    String toNumber = args[2];
    AuthTokenResponse response = GetYtelAuthorizationToken.getToken(userName, password);
    String accessToken = response.getAccessToken();
    YtelOtpResponse otpResponse = YtelApi.sendOtp(toNumber, accessToken);
    System.out.println("Otp response is " + otpResponse.getOtp());

  }

}
