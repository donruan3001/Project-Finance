package finance.repository;

import finance.domain.acounts.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryAccount extends JpaRepository<Account, Long> {
    Page<Account> findByUserId(Long userId, Pageable pageable);
}
