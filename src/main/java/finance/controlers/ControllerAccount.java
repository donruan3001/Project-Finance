package finance.controlers;

import finance.domain.acounts.Account;
import finance.dto.accounts.AccountCreateDTO;
import finance.dto.accounts.AccountResponseDTO;
import finance.dto.accounts.AccountUpdateDTO;
import finance.services.ServiceAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class ControllerAccount {

    @Autowired
    private final ServiceAccount serviceAccount;

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountCreateDTO data) {
         var accounts =serviceAccount.createAccount(data);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Page<AccountResponseDTO>> getAllAccounts(@PageableDefault(size = 10,page = 0)Pageable pageable) {
       var accounts = serviceAccount.getAllAccounts(pageable);
        return ResponseEntity.ok(accounts);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> patchAccount(@PathVariable Long id, @RequestBody AccountUpdateDTO data) {
        AccountResponseDTO updatedAccount =serviceAccount.patchAccount(id, data);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        serviceAccount.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }




}
