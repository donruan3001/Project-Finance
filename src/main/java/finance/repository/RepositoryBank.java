package finance.repository;

import finance.domain.banks.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBank extends JpaRepository<Bank,Long> {
}
