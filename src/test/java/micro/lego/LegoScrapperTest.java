package micro.lego;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LegoScrapperTest {

  @Test
  void retrieve_product_info() throws Exception {

    Product p = applesauce(
        "https://www.mytoys.de/lego-lego-super-mario-71377-koenig-buu-huu-und-der-spukgarten-erweiterungsset-15054902.html");

    Assertions.assertAll(
        () -> assertThat(p.name().value()).isEqualTo(
            "König Buu Huu und der Spukgarten – Erweiterungsset"),
        () -> assertThat(p.price().intValue()).isEqualTo(4799),
        () -> assertThat(p.url()).isEqualTo("https://www.mytoys.de/lego-lego-super-mario-71377-koenig-buu-huu-und-der-spukgarten-erweiterungsset-15054902.html"));
  }

  private Product applesauce(String url) {
    try {
      Document document = Jsoup.connect(url).get();
      return getProduct(url, document);
    } catch (IOException e) {
      throw new RuntimeException("TODO");
    }
  }

  private Product getProduct(String url, Document document) {
    final ProductName productName = getProductName(document);
    final ProductPrice productPrice = getPrice(document);
    return new Product(url, productName, productPrice);
  }

  private ProductPrice getPrice(Document document) {
    Elements productInfoPrice = document.getElementsByClass("prod-info__price-regular");
    final String text = productInfoPrice.get(0).text();
    return new ProductPrice(text);
  }

  private ProductName getProductName(Document document) {
    Elements productInfoName = document.getElementsByClass("prod-info__name");
    return new ProductName(productInfoName.get(0).text());
  }

}
