package finance.repository;

import finance.domain.banks.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryBank extends JpaRepository<Bank,Long> {
}
