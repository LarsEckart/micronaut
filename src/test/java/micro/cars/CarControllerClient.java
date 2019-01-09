package micro.cars;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/cars")
public interface CarControllerClient {

    @Get()
    String getAllCars();
}
