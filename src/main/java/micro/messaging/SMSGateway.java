package micro.messaging;

import io.reactivex.Flowable;

import java.util.Map;

public interface SMSGateway {

    Flowable<Map> send(String to, String text);
}
