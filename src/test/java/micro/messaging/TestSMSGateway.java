package micro.messaging;

import io.micronaut.context.annotation.Primary;

import java.util.Collections;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
@Primary
public class TestSMSGateway implements SMSGateway {

    @Override
    public Map send(String to, String text) {
        return Collections.singletonMap("anyKey", "anyValue");
    }
}
