package micro.bank;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class AccountService {

  private final AccountRepository repository;

  public AccountService(AccountRepository repository) {
    this.repository = repository;
  }

  public Account get(String iban) {
    return repository
        .findByIban(iban)
        .orElseThrow(() -> new NoSuchElementException("No account found with IBAN " + iban));
  }

  public List<Account> getAll() {
    return repository.findAll();
  }

  @Transactional
  public List<Account> transfer(String fromIban, String toIban, BigDecimal amount) {
    Account from = get(fromIban);
    Account to = get(toIban);
    from.setAmount(from.getAmount().subtract(amount));
    to.setAmount(to.getAmount().add(amount));
    repository.save(from);
    repository.save(to);
    return Arrays.asList(from, to);
  }
}
