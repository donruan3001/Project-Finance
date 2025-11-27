package finance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import finance.domain.acounts.Account;
import finance.domain.banks.Bank;
import finance.domain.user.User;
import finance.dto.accounts.AccountCreateDTO;
import finance.dto.accounts.AccountResponseDTO;
import finance.exceptions.UsernameNotFoundException;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryBank;
import finance.repository.RepositoryUser;
import finance.validator.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ServiceAccount {

    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryBank repositoryBank;
    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Transactional
    public AccountResponseDTO createAccount(AccountCreateDTO data) {

        String username=authenticatedUser.getUsername();


        User user = repositoryUser.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Bank bank = repositoryBank.findById(data.bankId())
                .orElseThrow(() -> new EntityNotFoundException("Banco não encontrado"));       

        var account = new Account(user,
                bank, data.name().trim(),
                data.type(), data.balance());

                
        repositoryAccount.save(account);

        return AccountResponseDTO.toDTO(account);
    }

    public Page<AccountResponseDTO> getMyAccounts(Pageable pageable) {

        String username=authenticatedUser.getUsername();
        
        User user = repositoryUser.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        
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

    // BUG FIX: Método estava vazio e causaria erro de compilação
    // Removido método incompleto - implementar quando necessário
    // Se precisar de atualização de conta, implementar com validações adequadas:
    // @Transactional
    // public AccountResponseDTO updateMyAccount(Long accountId, AccountUpdateDTO data) {
    //     String username = authenticatedUser.getUsername();
    //     Account account = repositoryAccount.findById(accountId)
    //             .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
    //     if (!account.getUser().getUsername().equals(username)) {
    //         throw new AccessDeniedException("Você não tem permissão para acessar esta conta");
    //     }
    //     // Atualizar campos...
    //     return AccountResponseDTO.toDTO(repositoryAccount.save(account));
    // }
}
