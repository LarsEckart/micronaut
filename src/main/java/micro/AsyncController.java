package micro;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Controller("/async")
public class AsyncController {

    @Get
    CompletableFuture<String> get() {
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().uri(URI.create("https://www.gov.uk/bank-holidays.json")).build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
