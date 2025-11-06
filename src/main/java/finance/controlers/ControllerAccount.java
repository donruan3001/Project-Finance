package finance.controlers;

import finance.dto.accounts.AccountCreateDTO;
import finance.dto.accounts.AccountResponseDTO;
import finance.services.ServiceAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class ControllerAccount {

    @Autowired
    private ServiceAccount serviceAccount;
    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountCreateDTO data) {
         var accounts =serviceAccount.createAccount(data);
        return ResponseEntity.ok(accounts);
    }
    @GetMapping
    public ResponseEntity<Page<AccountResponseDTO>> getMyAccounts(@PageableDefault(size = 10,page = 0)Pageable pageable) {
       var accounts = serviceAccount.getMyAccounts(pageable);
        return ResponseEntity.ok(accounts);
    }
}
