package micro;
import io.micronaut.context.annotation.Factory;
import java.util.Random;

import jakarta.inject.Singleton;

@Factory
public class BeanFactory {

    @Singleton
    Random random() {
        return new Random();
    }

}
