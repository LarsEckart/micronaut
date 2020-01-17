package micro;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import org.flywaydb.core.Flyway;

@Singleton
public class ApplicationStartEventListener implements ApplicationEventListener<ServerStartupEvent> {

  @Value("${datasources.default.url}")
  private String jdbcUrl;

  @Value("${datasources.default.username}")
  private String username;

  @Value("${datasources.default.password}")
  private String password;

  @Override
  public void onApplicationEvent(ServerStartupEvent event) {
    Flyway flyway = Flyway.configure().dataSource(jdbcUrl, username, password).load();
    flyway.migrate();
  }
}
