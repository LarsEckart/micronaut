package micro;

import java.util.Random;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class BeanFactory {

    @Singleton
    Random random() {
        return new Random(42);
    }

}
