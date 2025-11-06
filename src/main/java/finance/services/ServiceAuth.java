package finance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import finance.config.JWTService;
import finance.domain.user.User;
import finance.dto.ResponseJwtDTO;
import finance.dto.user.UserRegisterDTO;
import finance.repository.RepositoryUser;

@Service
public class ServiceAuth implements UserDetailsService {
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryUser.findByUsername(username);
    }

    public void register(UserRegisterDTO user) {
        if (repositoryUser.findByUsername(user.email()) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        String password = passwordEncoder.encode(user.password());
        User newUser = new User(user.name(), user.email(), password);   
        repositoryUser.save(newUser);
    }
    public ResponseJwtDTO login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = loadUserByUsername(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        String token = jwtService.createSecretKey((User) userDetails);
        ResponseJwtDTO responseJwtDTO = new ResponseJwtDTO(token);

        return responseJwtDTO;



    }
}


