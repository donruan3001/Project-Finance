package finance.services;

import finance.domain.user.User;
import finance.dto.user.UserProfileDTO;
import finance.dto.user.UserUpdateDTO;
import finance.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {
    
    @Autowired
    private RepositoryUser repositoryUser;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserProfileDTO getUserProfile(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        return new UserProfileDTO(
            user.getId(),
            user.getName(),
            user.getUsername(),
            user.getRole()
        );
    }
    
    @Transactional
    public UserProfileDTO updateUserProfile(Long userId, UserUpdateDTO data) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        if (data.name() != null && !data.name().trim().isEmpty()) {
            user.setName(data.name().trim());
        }
        
        if (data.email() != null && !data.email().trim().isEmpty()) {
            // Check if email is already taken by another user
            var existingUser = repositoryUser.findByUsername(data.email());
            if (existingUser != null && !((User) existingUser).getId().equals(userId)) {
                throw new IllegalArgumentException("Email já está em uso por outro usuário");
            }
            user.setUsername(data.email().trim());
        }
        
        if (data.password() != null && !data.password().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(data.password()));
        }
        
        var savedUser = repositoryUser.save(user);
        
        return new UserProfileDTO(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getUsername(),
            savedUser.getRole()
        );
    }
    
    @Transactional
    public void deleteUser(Long userId) {
        if (!repositoryUser.existsById(userId)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        
        repositoryUser.deleteById(userId);
    }
}