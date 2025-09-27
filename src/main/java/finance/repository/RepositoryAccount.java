package finance.repository;

import finance.domain.acounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositoryAccount extends JpaRepository<Account, Long> {}
