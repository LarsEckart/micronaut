package com.larseckart.kata;

import java.util.HashMap;
import java.util.Map;

class Decoder {

  public static RequestBody decode(String body) {
    Map<String, String> map = toQueryMap(body);

    return new RequestBody(map.get("grant_type"), map.get("username"), map.get("password"));
  }

  private static Map<String, String> toQueryMap(String query) {
    String[] params = query.split("&");
    Map<String, String> map = new HashMap<>();
    for (String param : params) {
      String[] currentParam = param.split("=");
      if (currentParam.length != 2) {
        continue;
      }
      String name = currentParam[0];
      String value = currentParam[1];
      map.put(name, value);
    }
    return map;
  }
}
