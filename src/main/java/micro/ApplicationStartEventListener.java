package micro;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;

@Singleton
public class ApplicationStartEventListener implements ApplicationEventListener<ServerStartupEvent> {

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {

    }
}
