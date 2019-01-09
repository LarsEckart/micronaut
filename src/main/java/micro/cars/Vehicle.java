package micro.cars;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class Vehicle {

    final Engine engine;

    Vehicle(@Named("v8") Engine engine) {
        this.engine = engine;
    }

    public String start() {
        return engine.start();
    }
}
