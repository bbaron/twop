package twop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.transaction.TestTransaction;

import javax.validation.ConstraintViolationException;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class AccountDatabaseConstraintsTest {

  @Autowired
  private AccountRepository repository;

  @Test
  void emailsMustBeUnique() {
    String email = "e@example.com";
    var a1 = new Account(email, "user1");
    var a2 = new Account(email, "user1");
    repository.saveAndFlush(a1);
    assertThrows(DataIntegrityViolationException.class, () -> repository.saveAndFlush(a2));
  }

  @Test
  void emailsMustQuackLikeAnEmail() {
    String email = "not-an-email";
    var a1 = new Account(email, "user1");
    assertThrows(ConstraintViolationException.class, () -> repository.saveAndFlush(a1));
  }

  @Test
  void crud() {
    TestTransaction.flagForCommit();
    String email = "crud@example.com";
    var a1 = new Account(email, "user1");
    repository.saveAndFlush(a1);
    TestTransaction.end();

    TestTransaction.start();
    TestTransaction.flagForCommit();
    var found  = repository.findByEmail(email).orElseThrow();
    found.setFavoriteShow("another show");
    assertEquals(a1, found);
    assertEquals(a1.getCreatedDate().truncatedTo(ChronoUnit.SECONDS),
        found.getCreatedDate().truncatedTo(ChronoUnit.SECONDS));
    TestTransaction.end();

    TestTransaction.start();
    TestTransaction.flagForCommit();
    repository.delete(a1);
    TestTransaction.end();
  }

}
