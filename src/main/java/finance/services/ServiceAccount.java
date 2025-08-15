package finance.services;

import finance.domain.acounts.Account;
import finance.domain.banks.Bank;
import finance.domain.user.User;
import finance.dto.accounts.AccountCreateDTO;
import finance.dto.accounts.AccountResponseDTO;
import finance.dto.accounts.AccountUpdateDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryBank;
import finance.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccount {

    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryBank repositoryBank;

    public Account createAccount(AccountCreateDTO data) {
        User user = repositoryUser.getReferenceById(data.userId());
        Bank bank = repositoryBank.getReferenceById(data.bankId());
        if (!repositoryUser.existsById(user.getId())) {
            throw new IllegalArgumentException("Usuário" + user.getId() + " não encontrado");
        }
        if (!repositoryBank.existsById(bank.getId())) {
            throw new IllegalArgumentException("Banco" + user.getId() + " não encontrado");
        }
        var account = new Account(user,
                bank, data.name().trim(),
                data.type(), data.balance());

        repositoryAccount.save(account);
        return account;

    }
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

        return dto;
    }
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

        public void deleteAccount(Long id) {
            if (!repositoryAccount.existsById(id)) {
                throw new IllegalArgumentException("Conta com ID " + id + " não encontrada");
            }
            repositoryAccount.deleteById(id);
        }


    }


