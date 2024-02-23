package com.larseckart.kata;

import static org.slf4j.LoggerFactory.getLogger;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.slf4j.Logger;

@Controller("/tokens")
class Tokens {

  private static final Logger log = getLogger(Tokens.class);

  @Inject
  RedisConfig redisConfig;

  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  @Post("/create")
  public Response createToken(@Body String body) {
    RedisClient redisClient = RedisClient.create(
        "rediss://" + redisConfig.getPassword() + "@" + redisConfig.getUrl() + ":"
            + redisConfig.getPort() + "/0");
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    RedisCommands<String, String> syncCommands = connection.sync();
    log.info("Received request to /tokens/create with body: " + body);
    RequestBody requestBody = Decoder.decode(body);
    String username = requestBody.username();
    String cacheKey = "server.tokens.tenant." + username;
    Map<String, String> existingToken = syncCommands.hgetall(cacheKey);
    if (!existingToken.isEmpty()) {
      log.info("Found existing toke n: " + existingToken);
      return Response.fromMap(existingToken);
    }

    // TODO: read request body and fill requested_by accordingly
    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
    Duration aliveUntil = Duration.ofSeconds(10);
    ZonedDateTime expires = now.plus(aliveUntil);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");
    String start = formatter.format(now);
    String end = formatter.format(expires);

    int minute = LocalTime.now().getMinute();
    int second = LocalTime.now().getSecond();

    String token = "abc" + minute + second;

    Response response = new Response(token, "bear", aliveUntil.getSeconds(), username, start, end);
    syncCommands.hset(cacheKey, response.toMap());
    syncCommands.expire(cacheKey, aliveUntil.getSeconds());
    log.info("Created token: " + token);
    connection.close();
    redisClient.shutdown();
    return response;
  }

  record Response(
      String access_token,
      String token_type,
      Long expires_in,
      String requested_by,
      String issued_at,
      String expires_at) {

    Map<String, String> toMap() {
      return Map.of(
          "access_token", access_token,
          "token_type", token_type,
          "expires_in", expires_in.toString(),
          "requested_by", requested_by,
          "issued_at", issued_at,
          "expires_at", expires_at);
    }

    static Response fromMap(Map<String, String> map) {
      return new Response(
          map.get("access_token"),
          map.get("token_type"),
          Long.parseLong(map.get("expires_in")),
          map.get("requested_by"),
          map.get("issued_at"),
          map.get("expires_at"));
    }
  }

}
