package micro.bank;

import java.math.BigDecimal;
import java.util.List;

import io.honeycomb.beeline.tracing.Beeline;
import io.honeycomb.beeline.tracing.Span;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;

@Controller
public class AccountController {

  private final AccountService service;
  private final Beeline beeline;

  public AccountController(AccountService service, Beeline beeline) {
    this.service = service;
    this.beeline = beeline;
  }

  @Get("/account/{iban}")
  public Account read(@PathVariable("iban") String iban) {
    try{
      Span span = beeline.getActiveSpan();
      span.addField("iban", iban);
      return service.get(iban);
    } finally {
      beeline.getTracer().endTrace();
    }
  }

  @Get("/account")
  public List<Account> read() {
    return service.getAll();
  }

  @Put("/account/{fromIban}/transfer/{toIban}/{amount}")
  public List<Account> transfer(
      @PathVariable("fromIban") String fromIban,
      @PathVariable("toIban") String toIban,
      @PathVariable("amount") BigDecimal amount) {
    return service.transfer(fromIban, toIban, amount);
  }
}
