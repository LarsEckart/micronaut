package micro.bank;

import java.util.List;
import java.util.Optional;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.H2)
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByIban(String iban);

    @Query("SELECT * FROM account")
    List<Account> findAll();
}
