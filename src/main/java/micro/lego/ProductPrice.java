package micro.lego;

class ProductPrice {

  private final String value;

  public ProductPrice(String input) {
    this.value = input;
  }

  public int intValue() {
    String pattern = "(([0-9]+),([0-9]+)).*";
    final String price = this.value.replaceAll(pattern, "$1");
    final String inCents = price.replace(",", "");
    return Integer.parseInt(inCents);
  }

  @Override
  public String toString() {
    return value;
  }

}
