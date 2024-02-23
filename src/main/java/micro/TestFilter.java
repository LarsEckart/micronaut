package micro;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.FilterChain;
import io.micronaut.http.filter.HttpFilter;
import io.opentelemetry.api.trace.Span;
import org.reactivestreams.Publisher;

@Filter("/**")
public class TestFilter implements HttpFilter {

  @Override
  public Publisher<? extends HttpResponse<?>> doFilter(HttpRequest<?> request, FilterChain chain) {
    String tenantId = request.getHeaders().get("tenant-id");
    Span span = Span.current();
    span.setAttribute("tenant.id", tenantId);
    return chain.proceed(request);
  }
}
