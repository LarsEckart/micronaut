package micro;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

import java.util.Random;

@Factory
public class BeanFactory {

    @Singleton
    Random random() {
        return new Random();
    }

}
