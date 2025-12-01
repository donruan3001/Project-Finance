package finance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import finance.domain.acounts.Account;
import finance.domain.user.User;
import finance.dto.accounts.AccountCreateDTO;
import finance.dto.accounts.AccountResponseDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryUser;
import finance.validator.AuthenticatedUser;
import jakarta.transaction.Transactional;

@Service
public class ServiceAccount {

    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private RepositoryUser repositoryUser;
    
    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Transactional
    public AccountResponseDTO createAccount(AccountCreateDTO data) {

        String username=authenticatedUser.getUsername();


        User user = repositoryUser.findByUsername(username);

        var account = new Account(user, data.name(), data.type(), data.balance());
                
        repositoryAccount.save(account);

        return AccountResponseDTO.toDTO(account);
    }

    public Page<AccountResponseDTO> getMyAccounts(Pageable pageable) {

        String username=authenticatedUser.getUsername();
        
        User user = repositoryUser.findByUsername(username);

        
        Page<Account> accounts = repositoryAccount.findByUserId(user.getId(), pageable);

        return accounts.map(account -> new AccountResponseDTO(
                        account.getId(),
                        account.getUser().getId(),
                        account.getName(),
                        account.getType(),
                        account.getBalance(),
                        account.getCreatedAt()
                ));
 
}
}
