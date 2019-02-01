package micro;

import javax.inject.Inject;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class HelloControllerTest {

    @Inject
    HelloClient helloClient;

    @Test
    void testHello() {
        assertThat(helloClient.hello("Fred")).isEqualTo("Hello Fred!");
    }

    @Test
    void method_name_index_results_in_root_path() {
        assertThat(helloClient.index()).isEqualTo("Gute Nacht");
    }
}
