package finance.controlers;

import finance.dto.accounts.AccountCreateDTO;
import finance.services.ServiceAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class ControllerAccount {

    @Autowired
    private final ServiceAccount serviceAccount;

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountCreateDTO data) {
         serviceAccount.createAccount(data);
        return ResponseEntity.ok().build();
    }



}
