package micro;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotBlank;

@Client("/hello")
public interface HelloClient {

    @Get("/{name}")
    String hello(@NotBlank String name);

    @Get()
    String index();

    @Get("/header")
    String helloHeader(@Header("x-name") String name);
}
