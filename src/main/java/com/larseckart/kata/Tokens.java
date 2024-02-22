package com.larseckart.kata;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller("/tokens")
class Tokens {

  @Post("/create")
  public Response createToken() {
    ZonedDateTime now = ZonedDateTime.now();
    Duration aliveUntil = Duration.ofMinutes(2);
    ZonedDateTime expires = now.plus(aliveUntil);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");
    String start = formatter.format(now);
    String end = formatter.format(expires);

    return new Response("abc", "bear", aliveUntil.getSeconds(), "you", start, end);
  }

  record Response(
      String access_token,
      String token_type,
      Long expires_in,
      String requested_by,
      String issued,
      String expires) {
  }

}
