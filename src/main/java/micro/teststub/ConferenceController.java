package micro.teststub;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;
import java.util.Optional;

@Controller("/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;

    public ConferenceController(final ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Get("/")
    public List<Conference> all() {
        return conferenceService.all();
    }

    @Get("/{name}")
    public Optional<Conference> findByName(final String name) {
        return conferenceService.findByName(name);
    }
}
