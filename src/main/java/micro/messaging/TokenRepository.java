package micro.messaging;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
class TokenRepository {

  private TokenGenerator tokenGenerator;

  private Map<String, String> tokens = new LinkedHashMap<>();

  TokenRepository(TokenGenerator tokenGenerator) {
    this.tokenGenerator = tokenGenerator;
  }

  public String getToken(String key) {
    tokens.putIfAbsent(key, tokenGenerator.next());
    return tokens.get(key);
  }

  public boolean validToken(String key, String token) {
    return token.equals(tokens.get(key));
  }
}
