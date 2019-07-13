package micro.messaging;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("twilio")
class Config {

    @NotBlank
    String authToken;

    @NotBlank
    String accountSid;
}
