package micro.cars;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import micro.cars.Vehicle;

@Controller("/cars")
public class CarController {

    private final Vehicle vehicle;

    public CarController(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Get(uri="/all", produces = MediaType.TEXT_PLAIN)
    public String getAllCars() {
        return vehicle.start();
    }

}