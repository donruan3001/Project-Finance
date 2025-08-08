package finance.domain.user;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="users")
@Table(name="usuarios")
public class User implements UserDetails{

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

private String name;
private String username;
private String password;
@Enumerated(EnumType.STRING)
private RoleUser role;

        public User(String name, String email, String password,RoleUser role) {
            this.name = name;
            this.username = email;
            this.password = password;
            this.role = role;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role==RoleUser.ADMIN) return List.of(new  SimpleGrantedAuthority("ROLE_ADMIN"), 
        new SimpleGrantedAuthority("ROLE_USER"));
        else  return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
       return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
