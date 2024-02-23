package com.larseckart.kata;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("redis")
class RedisConfig {

  private String url;
  private int port;
  private String password;
  private boolean test;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isTest() {
    return test;
  }

  public void setTest(boolean test) {
    this.test = test;
  }
}
