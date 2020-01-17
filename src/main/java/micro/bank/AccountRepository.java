package micro.bank;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository
public abstract class AccountRepository implements CrudRepository<Account, Long> {

  private final JdbcOperations jdbcOperations;

  public AccountRepository(JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Transactional
  Optional<Account> findByIban(String iban) {
    String sql = "SELECT * FROM account AS a WHERE a.iban = ?";
    return jdbcOperations.prepareStatement(
        sql,
        statement -> {
          statement.setString(1, iban);
          ResultSet resultSet = statement.executeQuery();
          return jdbcOperations.entityStream(resultSet, Account.class).findFirst();
        });
  }

  @Transactional
  public List<Account> findAll() {
    String sql = "SELECT * FROM account";
    return jdbcOperations.prepareStatement(
        sql,
        statement -> {
          ResultSet resultSet = statement.executeQuery();
          return jdbcOperations.entityStream(resultSet, Account.class).collect(Collectors.toList());
        });
  }
}
