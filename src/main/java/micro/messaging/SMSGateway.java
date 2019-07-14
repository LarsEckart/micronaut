package micro.messaging;

import io.reactivex.Single;

import java.util.Map;

public interface SMSGateway {

    Single<Map<String, String>> send(String to, String text);
}
