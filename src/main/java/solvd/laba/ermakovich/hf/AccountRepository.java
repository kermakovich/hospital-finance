package solvd.laba.ermakovich.hf;

import org.springframework.data.jpa.repository.JpaRepository;
import solvd.laba.ermakovich.hf.domain.Account;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByExternalId(UUID externalId);

    Optional<Account> findByExternalId(UUID employeeId);

}
