package micro.lego;

class ProductName {

  private final String value;

  public ProductName(String input) {
    this.value = input;
  }

  public String value() {
    String pattern = "(LEGO® Super Mario )(\\d*) (.*)";
    return this.value.replaceAll(pattern, "$3");
  }

  @Override
  public String toString() {
    return value();
  }
}
