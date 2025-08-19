package finance.controlers;

import finance.dto.ResponseJwtDTO;
import finance.dto.user.UserLoginDTO;
import finance.dto.user.UserRegisterDTO;
import finance.repository.RepositoryUser;
import finance.services.ServiceAuth;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.created(null).build();
    }



    @PostMapping("/login")
    public ResponseEntity<ResponseJwtDTO> login(@RequestBody @Valid UserLoginDTO user){
        ResponseJwtDTO token= serviceAuth.login(user.email(),  user.password());
        return ResponseEntity.ok(token);



    }


}
