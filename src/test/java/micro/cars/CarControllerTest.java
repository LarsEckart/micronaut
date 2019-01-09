package micro.cars;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class CarControllerTest {

    @Inject
    CarControllerClient client;

    @Test
    void return_start_text_from_injected_qualified_engine() {
        assertThat(client.getAllCars()).isEqualTo("Starting V8");
    }
}
