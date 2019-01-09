package micro.teststub;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class ConferenceControllerTest {

    @Inject
    ConferenceClient client;

    @Test
    void scenario_expectedbehaviour() {
        assertThat(client.all()).hasSize(1);
    }
}
