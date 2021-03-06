package micro.messaging;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(AuthConfiguration.PREFIX)
class AuthConfiguration {

  static final String PREFIX = "myauth";

  @NotBlank String sender;
}
