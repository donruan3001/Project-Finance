package finance.validators.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryAccount;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private RepositoryAccount repositoryAccount;

    @Override
    public void validate(TransactionCreateDTO data) {
        if (data.accountId() == null) {
            throw new IllegalArgumentException("ID da conta é obrigatório");
        }
        
        repositoryAccount.findById(data.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Conta " + data.accountId() + " não encontrada"));
    }

}
