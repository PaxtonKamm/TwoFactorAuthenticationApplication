package com.ytel.example.otp;

import java.io.Serializable;
import com.google.gson.JsonArray;

public class ResponseWrapper<T> implements Serializable {
  private static final long serialVersionUID = 788363194253499334L;
  public Boolean status;
  public Integer count;
  public Integer page;
  public JsonArray payload;

}
