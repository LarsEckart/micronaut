package micro;

import javax.inject.Singleton;

import io.honeycomb.beeline.tracing.Beeline;
import io.honeycomb.beeline.tracing.SpanBuilderFactory;
import io.honeycomb.beeline.tracing.SpanPostProcessor;
import io.honeycomb.beeline.tracing.Tracer;
import io.honeycomb.beeline.tracing.Tracing;
import io.honeycomb.beeline.tracing.sampling.Sampling;
import io.honeycomb.libhoney.HoneyClient;
import io.honeycomb.libhoney.LibHoney;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;

@Factory
public class HoneyFactory {

  @Value("honey.key")
  private String key;

  private static final String DATASET = "micronaut";

  private HoneyClient client;
  private Beeline beeline;

  @Singleton
  Beeline beeline() {
    client = LibHoney.create(LibHoney.options().setDataset(DATASET).setWriteKey(key).build());
    SpanPostProcessor postProcessor = Tracing.createSpanProcessor(client, Sampling.alwaysSampler());
    SpanBuilderFactory factory = Tracing.createSpanBuilderFactory(postProcessor, Sampling.alwaysSampler());
    Tracer tracer = Tracing.createTracer(factory);
    beeline = Tracing.createBeeline(tracer, factory);
    return beeline;
  }
}
