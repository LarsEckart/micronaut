package micro.bank;

import java.math.BigDecimal;
import java.util.List;

import io.honeycomb.beeline.tracing.Beeline;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;
import io.micronaut.tracing.annotation.NewSpan;
import io.micronaut.tracing.annotation.SpanTag;

@Controller
public class AccountController {

  private final AccountService service;
  private final Beeline beeline;

  public AccountController(AccountService service, Beeline beeline) {
    this.service = service;
    this.beeline = beeline;
  }

  @NewSpan
  @Get("/account/{iban}")
  public Account read(@SpanTag("iban") @PathVariable("iban") String iban) {
    return service.get(iban);
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
