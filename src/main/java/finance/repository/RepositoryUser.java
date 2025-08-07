package finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import finance.domain.user.User;

public interface  RepositoryUser extends JpaRepository<User,Long> {
    
User findByUsername(String username);

}
