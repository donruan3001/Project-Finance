package finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import finance.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface  RepositoryUser extends JpaRepository<User,Long> {
    
UserDetails findByUsername(String username);

}
