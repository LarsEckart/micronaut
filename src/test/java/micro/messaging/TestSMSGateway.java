package micro.messaging;

import io.micronaut.context.annotation.Primary;
import io.reactivex.Flowable;

import java.util.Collections;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
@Primary
public class TestSMSGateway implements SMSGateway {

    @Override
    public Flowable<Map> send(String to, String text) {
        return Flowable.just(Collections.singletonMap("anyKey", "anyValue"));
    }
}
