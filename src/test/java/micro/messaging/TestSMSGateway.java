package micro.messaging;

import io.micronaut.context.annotation.Primary;
import io.reactivex.Single;

import java.util.Collections;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
@Primary
public class TestSMSGateway implements SMSGateway {

    @Override
    public Single<Map<String, String>> send(String to, String text) {
        return Single.just(Collections.singletonMap("anyKey", "anyValue"));
    }
}
