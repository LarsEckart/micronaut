package micro.lego;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProductNameTest {

  @Test
  void from_full_name_to_my_name() {
    String input = "LEGO® Super Mario 71377 König Buu Huu und der Spukgarten Erweiterungsset";

    var productName = new ProductName(input);

    assertThat(productName.value()).isEqualTo("König Buu Huu und der Spukgarten Erweiterungsset");
  }
}
