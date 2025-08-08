package finance.controlers;

import finance.config.JWTService;
import finance.domain.user.User;
import finance.dto.ResponseJwtDTO;
import finance.dto.UserLoginDTO;
import finance.dto.UserRegisterDTO;
import finance.repository.RepositoryUser;
import finance.services.ServiceAuth;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private ServiceAuth serviceAuth;


    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserRegisterDTO user){
        serviceAuth.register(user);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/login")
    public ResponseEntity<ResponseJwtDTO> login(@RequestBody @Valid UserLoginDTO user){
        serviceAuth.login(user);

        return ResponseEntity.ok(new ResponseJwtDTO(token));
    }


}
