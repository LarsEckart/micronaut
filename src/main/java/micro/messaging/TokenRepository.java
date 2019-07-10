package micro.messaging;

import java.util.Map;
import java.util.Optional;
import javax.inject.Singleton;

@Singleton
public class TokenRepository {

    private Map<String, String> tokens = Map.of("123", "secret321", "456", "secret654");

    public Optional<String> getToken(String key) {
        return Optional.ofNullable(tokens.get(key));
    }
}
