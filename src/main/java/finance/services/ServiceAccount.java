package finance.services;

import finance.domain.acounts.Account;
import finance.domain.banks.Bank;
import finance.domain.user.User;
import finance.dto.accounts.AccountCreateDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryBank;
import finance.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccount {

    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryBank repositoryBank;

    public Account createAccount(AccountCreateDTO data){
        User user= repositoryUser.getReferenceById(data.userId());
        Bank bank = repositoryBank.getReferenceById(data.bankId());
        if(!repositoryUser.existsById(user.getId())){
            throw new IllegalArgumentException("Usuário"+ user.getId() + " não encontrado");
        }

        if(!repositoryBank.existsById(bank.getId())){
            throw new IllegalArgumentException("Banco" +user.getId()+ " não encontrado");
        }


        var account = new Account(user,
                bank, data.name().trim(),
                data.type(), data.balance());

        repositoryAccount.save(account);
        return account;



    }

}
