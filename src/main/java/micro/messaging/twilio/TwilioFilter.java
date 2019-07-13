package micro.messaging.twilio;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

@Filter("/**")
@Requires(property = Config.PREFIX + ".account.sid")
@Requires(property = Config.PREFIX + ".auth.token")
public class TwilioFilter implements HttpClientFilter {

    private final Config configuration;

    public TwilioFilter(Config configuration) {
        this.configuration = configuration;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        return chain.proceed(request.basicAuth(configuration.accountSid, configuration.authToken));
    }
}
