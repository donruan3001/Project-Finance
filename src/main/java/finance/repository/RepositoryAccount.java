package finance.repository;

import finance.domain.acounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAccount extends JpaRepository<Account, Long> {

}
