package micro.teststub;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

@Singleton
public class ConferenceDataRepository implements ConferenceService {

    private final static List<Conference> CONFERENCES =
            Arrays.asList(new Conference("Gr8Conf EU", "Copenhagen"),
                    new Conference("Gr8Conf US", "Minneapolis"),
                    new Conference("Greach", "Madrid"));

    public List<Conference> all() {
        return CONFERENCES;
    }

    public Optional<Conference> findByName(final String name) {
        return all().stream().filter(c -> name.equals(c.getName())).findFirst();
    }
}
