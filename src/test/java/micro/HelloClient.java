package micro;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotBlank;

@Client("/")
public interface HelloClient {

    @Get("/hello/{name}")
    String hello(@NotBlank String name);
}
