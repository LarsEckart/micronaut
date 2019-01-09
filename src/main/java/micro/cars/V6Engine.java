package micro.cars;

import javax.inject.Singleton;

@Singleton
class V6Engine implements Engine {

    public String start() {
        return "Starting V6";
    }
}
