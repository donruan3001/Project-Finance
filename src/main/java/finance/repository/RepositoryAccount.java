package finance.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import finance.domain.acounts.Account;


public interface RepositoryAccount extends JpaRepository<Account, Long> {
    Page<Account> findByUserId(Long userId, Pageable pageable);

    Optional<Account> findByUserUsername(String username);
    
}
