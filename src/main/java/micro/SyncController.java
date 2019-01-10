package micro;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller("/sync")
public class SyncController {

    @Get
    String get() throws IOException, InterruptedException {
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().uri(URI.create("https://www.gov.uk/bank-holidays.json")).build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}
