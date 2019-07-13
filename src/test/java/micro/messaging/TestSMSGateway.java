package micro.messaging;

import io.micronaut.context.annotation.Primary;

import javax.inject.Singleton;

@Singleton
@Primary
public class TestSMSGateway implements SMSGateway {

    @Override
    public void send() {

    }
}
