package micro.ioc;

import javax.inject.Singleton;

@Singleton
class V8Engine implements Engine {

    int cylinders = 8;

    public String start() {
        return "Starting V8";
    }
}
