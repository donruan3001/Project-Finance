package finance.services;

import finance.domain.acounts.Account;
import finance.domain.banks.Bank;
import finance.domain.user.User;
import finance.dto.accounts.AccountCreateDTO;
import finance.dto.accounts.AccountResponseDTO;
import finance.dto.accounts.AccountUpdateDTO;

import finance.exceptions.IdBankNotFoundException;
import finance.exceptions.IdUserNotFoundException;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryBank;
import finance.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccount {

    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryBank repositoryBank;


    @PreAuthorize("#data.userId() == authentication.principal.id or hasRole('ADMIN')")
    @Transactional
    public Account createAccount(AccountCreateDTO data) {
        User user = repositoryUser.getReferenceById(data.userId());
        Bank bank = repositoryBank.getReferenceById(data.bankId());
        if (!repositoryUser.existsById(user.getId())) {
            throw new IllegalArgumentException("id do user :"+data.bankId()+" não encontrado");
        }
        if (!repositoryBank.existsById(bank.getId())) {
            throw new IllegalArgumentException("id do banco: '"+data.bankId()+"' não encontrado");
        }
        var account = new Account(user,
                bank, data.name().trim(),
                data.type(), data.balance());

        repositoryAccount.save(account);
        return account;

    }


    @PreAuthorize("#data.userId() == authentication.principal.id or hasRole('ADMIN')")
    @Transactional
    public AccountResponseDTO patchAccount(Long id, AccountUpdateDTO data) {
        Account account = repositoryAccount.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta com ID " + id + " não encontrada"));

        // Atualiza apenas os campos fornecidos, preservando os existentes
        if (data.name() != null) account.setName(data.name().trim());
        if (data.type() != null) account.setType(data.type());
        if (data.balance() != null) account.setBalance(data.balance());

        account = repositoryAccount.save(account);
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
    @Transactional
    @PreAuthorize("#id")
    public void deleteAccount(Long id) {
        if (!repositoryAccount.existsById(id)) {
            throw new IllegalArgumentException("Conta com ID " + id + " não encontrada");
        }
        repositoryAccount.deleteById(id);
    }



}


