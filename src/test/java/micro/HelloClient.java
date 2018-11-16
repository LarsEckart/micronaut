package micro;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import javax.validation.constraints.NotBlank;

@Client("/")
public interface HelloClient {
    @Get("/hello/{name}")
    Single<String> hello(@NotBlank String name);
}
