package micro.lego;

import static org.slf4j.LoggerFactory.getLogger;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;

@Controller("/lego")
class LegoScrapper {

  private static final Logger log = getLogger(LegoScrapper.class);

  @Get(produces = MediaType.TEXT_PLAIN)
  public HttpResponse index() {
    final Product applesauce = new LegoScrapper().applesauce(
        "https://www.mytoys.de/lego-lego-super-mario-71377-koenig-buu-huu-und-der-spukgarten-erweiterungsset-15054902.html");
    final String response = applesauce.name() + " " + applesauce.price();
    log.info(response);
    return HttpResponse.ok(response);
  }


  public Product applesauce(String url) {
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
