package com.larseckart.kata;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Factory
public class BeanFactory {

  @Inject
  RedisConfig redisConfig;

  @Singleton
  public StatefulRedisConnection<String, String> getStatefulRedisConnection() {
    RedisClient redisClient = RedisClient.create(
        "rediss://" + redisConfig.getPassword() + "@" + redisConfig.getUrl() + ":"
            + redisConfig.getPort() + "/0");
    return redisClient.connect();
  }
}
