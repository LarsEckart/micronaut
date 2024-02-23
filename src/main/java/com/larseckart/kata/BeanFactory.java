package com.larseckart.kata;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Factory
public class BeanFactory {

  @Inject
  RedisConfig config;

  @Singleton
  public StatefulRedisConnection<String, String> getStatefulRedisConnection() {
    if (config.isTest()) {
      var redisClient = RedisClient.create(config.getUrl() + "/0");
      return redisClient.connect();
    }
    var redisClient = RedisClient.create(
        "rediss://" + config.getPassword() + "@" + config.getUrl() + ":" + config.getPort() + "/0");

    return redisClient.connect();
  }
}
