package micro.teststub;

import java.util.List;
import java.util.Optional;

public interface ConferenceService {

    List<Conference> all();

    Optional<Conference> findByName(String name);

}
