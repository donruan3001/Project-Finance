package finance.repository;

import finance.domain.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryTransactions extends JpaRepository<Transaction,Long> {


}
