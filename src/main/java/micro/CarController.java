package micro;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import micro.ioc.Vehicle;

@Controller("/cars")
public class CarController {

    private final Vehicle vehicle;

    public CarController(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Get(uri="/", produces = MediaType.TEXT_PLAIN)
    public String getAllCars() {
        return vehicle.start();
    }

}
