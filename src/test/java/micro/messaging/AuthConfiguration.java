package micro.messaging;

import io.micronaut.context.annotation.Primary;

@Primary
public class AuthConfiguration {

    static final String PREFIX = "myauth";

    String sender = "secretSender";
}
