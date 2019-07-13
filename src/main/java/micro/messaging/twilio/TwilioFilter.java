package micro.messaging.twilio;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

@Filter("/**")
@Requires(property = Config.PREFIX + ".accountSid")
@Requires(property = Config.PREFIX + ".authToken")
public class TwilioFilter  implements HttpClientFilter {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TwilioFilter.class);

    private final Config configuration;

    public TwilioFilter(Config configuration ) {
        this.configuration = configuration;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        log.info("filter " + configuration.accountSid);
        return chain.proceed(request.basicAuth(configuration.accountSid, configuration.authToken));
    }
}
