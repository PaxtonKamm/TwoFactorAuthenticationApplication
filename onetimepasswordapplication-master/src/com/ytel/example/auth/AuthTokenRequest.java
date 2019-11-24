package com.ytel.example.auth;

public class AuthTokenRequest {
  private String username;
  private String password;
  private String grantType = "resource_owner_credentials";

  public AuthTokenRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public String getGrantType(){
    return grantType;
  }

}
