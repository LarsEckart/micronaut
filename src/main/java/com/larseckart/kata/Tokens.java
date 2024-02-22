package com.larseckart.kata;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller("/tokens")
class Tokens {

  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  @Post("/create")
  public Response createToken() {
    // TODO: read request body and fill requested_by accordingly
    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
    Duration aliveUntil = Duration.ofMinutes(2);
    ZonedDateTime expires = now.plus(aliveUntil);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");
    String start = formatter.format(now);
    String end = formatter.format(expires);

    int minute = LocalTime.now().getMinute();

    return new Response("abc" + minute, "bear", aliveUntil.getSeconds(), "you", start, end);
  }

  record Response(
      String access_token,
      String token_type,
      Long expires_in,
      String requested_by,
      String issued_at,
      String expires_at) {

  }

}
