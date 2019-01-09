package micro.ioc;

import javax.inject.Singleton;

@Singleton
public class Vehicle {

    final Engine engine;

    Vehicle(Engine engine) {
        this.engine = engine;
    }

    public String start() {
        return engine.start();
    }
}
