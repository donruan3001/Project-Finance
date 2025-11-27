package finance.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.domain.transactions.Transaction;
@Repository
public interface RepositoryTransactions extends JpaRepository<Transaction,Long> {

    Optional<Transaction> findByAccountId(Long accountId);
    Page<Transaction> findByAccountId(Long accountId, Pageable pageable);
}
