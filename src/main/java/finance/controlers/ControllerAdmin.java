package finance.controlers;

import finance.domain.user.User;
import finance.dto.accounts.AccountResponseDTO;
import finance.dto.user.UserResponseDTO;
import finance.services.ServiceAccount;
import finance.services.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ControllerAdmin {

    @Autowired
    private ServiceAdmin serviceAdmin;
    @Autowired
    private ServiceAccount serviceAccount;

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        Page<UserResponseDTO> users = serviceAdmin.listAllUsers(pageable);
        return ResponseEntity.ok(users);
    }
    @GetMapping
    public ResponseEntity<Page<AccountResponseDTO>> getAllAccounts(@PageableDefault(size = 10,page = 0)Pageable pageable) {
        var accounts = serviceAdmin.getAllAccounts(pageable);
        return ResponseEntity.ok(accounts);
    }


}
