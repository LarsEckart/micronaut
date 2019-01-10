package micro;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/issues")
public interface IssuesClient {

    @Get("/{number}")
    String issue(Integer number);
}
