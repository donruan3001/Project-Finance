package finance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import finance.domain.user.User;

public interface  RepositoryUser extends JpaRepository<User,Long> {
    
Optional<User> findByUsername(String username);

}
