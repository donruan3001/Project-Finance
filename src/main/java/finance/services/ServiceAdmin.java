package finance.services;

import finance.domain.acounts.Account;
import finance.dto.accounts.AccountResponseDTO;
import finance.dto.user.UserRegisterDTO;
import finance.dto.user.UserResponseDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ServiceAdmin {

    @Autowired
    RepositoryUser repositoryUser;
    @Autowired
    RepositoryAccount repositoryAccount;

    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserResponseDTO> listAllUsers(Pageable paginacao){

         Page<UserResponseDTO> page = repositoryUser.findAll(paginacao).map(user-> new UserResponseDTO(
                 user.getId(),
                 user.getName(),
                 user.getUsername(),
                 user.getRole()
         ));
         return page;

    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<AccountResponseDTO> getAllAccounts(Pageable pageable) {
        Page<Account> accounts = repositoryAccount.findAll(pageable);
        var dto= accounts.map(account -> new AccountResponseDTO(
                account.getId(),
                account.getUser().getId(),
                account.getBank().getId(),
                account.getName(),
                account.getType(),
                account.getBalance(),
                account.getCreatedAt()));
        if(accounts.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma conta encontrada");
        }
        return dto;
    }





}
