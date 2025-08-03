package finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import finance.domain.user.User;

public interface  RepositoryUser extends JpaRepository<User,Long> {
    
UserDetails findByEmail(String email);

}
