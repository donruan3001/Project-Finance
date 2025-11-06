package finance.services;

import finance.config.JWTService;
import finance.domain.acounts.Account;
import finance.domain.banks.Bank;
import finance.domain.user.User;
import finance.dto.accounts.AccountCreateDTO;
import finance.dto.accounts.AccountResponseDTO;


import finance.repository.RepositoryAccount;
import finance.repository.RepositoryBank;
import finance.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccount {

    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryBank repositoryBank;
    @Autowired
    private JWTService jwtService;

    @Transactional
    public AccountResponseDTO createAccount(AccountCreateDTO data) {

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();


        User user = repositoryUser.findByUsername(username);

        Bank bank = repositoryBank.findById(data.bankId())
                .orElseThrow(() -> new RuntimeException("Banco com ID " + data.bankId() + " não encontrado"));

        if(!user.getUsername().equals(username)) {
            throw new RuntimeException("Usuário autenticado não corresponde ao ID fornecido");
        }

        var account = new Account(user,
                bank, data.name().trim(),
                data.type(), data.balance());

        repositoryAccount.save(account);

        return new AccountResponseDTO(
                account.getId(),
                account.getUser().getId(),
                account.getBank().getId(),
                account.getName(),
                account.getType(),
                account.getBalance(),
                account.getCreatedAt()
        );
    }
    public Page<AccountResponseDTO> getMyAccounts(Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();

        User user = repositoryUser.findByUsername(username);
        
        Page<Account> accounts = repositoryAccount.findByUserId(user.getId(), pageable);

        return accounts.map(account -> new AccountResponseDTO(
                        account.getId(),
                        account.getUser().getId(),
                        account.getBank().getId(),
                        account.getName(),
                        account.getType(),
                        account.getBalance(),
                        account.getCreatedAt()
                ));
 
}
}
