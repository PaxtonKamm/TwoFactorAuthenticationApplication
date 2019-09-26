package com.ytel.example.auth;

public class AuthTokenResponse {

  private String accessToken;
  private String refreshToken;

  public AuthTokenResponse(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public void setAccessToken(String token) {
    token = accessToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setRefreshToken(String token) {
    token = refreshToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

}
