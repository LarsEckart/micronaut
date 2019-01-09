package micro.teststub;

import io.micronaut.context.annotation.Primary;

import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

@Singleton
@Primary
class ConferenceServiceStub implements ConferenceService {

    public List<Conference> all() {
        return List.of(new Conference("Gr8Conf", "Copenhagen"));
    }

    public Optional<Conference> findByName(final String name) {
        if ("Gr8Conf".equals(name)) {
            return Optional.of(new Conference("Gr8Conf", "Copenhagen"));
        } else {
            return Optional.empty();
        }
    }
}

