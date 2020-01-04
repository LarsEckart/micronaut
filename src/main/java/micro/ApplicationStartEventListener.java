package micro;

import javax.inject.Singleton;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import org.flywaydb.core.Flyway;

@Singleton
public class ApplicationStartEventListener implements ApplicationEventListener<ServerStartupEvent> {

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:./target/foobar", "sa", null).load();

        flyway.migrate();
    }
}

