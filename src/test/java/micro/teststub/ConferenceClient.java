package micro.teststub;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;
import java.util.Optional;

@Client("/conference")
interface ConferenceClient {

    @Get("/")
    List<Conference> all();

    @Get("/{name}")
    Optional<Conference> findByName(final String name);
}

