package twop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByEmail(String email);
}
