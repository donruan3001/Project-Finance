package finance.repository;

import finance.domain.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTransactions extends JpaRepository<Transaction,Long> {
}
