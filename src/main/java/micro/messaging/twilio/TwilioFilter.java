package micro.messaging.twilio;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

@Filter("/**")
@Requires(property = TwilioConfig.PREFIX + ".account.sid")
@Requires(property = TwilioConfig.PREFIX + ".auth.token")
class TwilioFilter implements HttpClientFilter {

    private final TwilioConfig configuration;

    public TwilioFilter(TwilioConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        return chain.proceed(request.basicAuth(configuration.accountSid, configuration.authToken));
    }
}
