package micro;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class IssuesControllerTest {

    @Inject
    IssuesClient client;

    @Test
    void path_with_integer_returns_result() {
        assertThat(client.issue(1)).isEqualTo("Issue # 1!");
    }

    @Test
    void path_with_wrong_type_parameter_returns_error() {
        assertThat(client.issue(1)).isEqualTo("Issue # 1!");
    }
}
