package micro.messaging;

import java.util.Map;

public interface SMSGateway {

    Map send(String to, String text);
}
