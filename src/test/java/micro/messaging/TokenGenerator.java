package micro.messaging;

import io.micronaut.context.annotation.Primary;

import javax.inject.Singleton;

@Singleton
@Primary
public class TokenGenerator {

    String next() {
        return "42";
    }
}
