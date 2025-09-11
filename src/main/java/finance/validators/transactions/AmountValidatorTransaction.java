package finance.validators.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finance.domain.acounts.Account;
import finance.domain.transactions.TypeTransaction;
import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryAccount;

@Component
public class AmountValidatorTransaction implements Validator {
    
    @Autowired
    private RepositoryAccount repositoryAccount;

    @Override
    public void validate(TransactionCreateDTO data) {
        // Só valida saldo para despesas
    
    Account account = repositoryAccount.findById(data.accountId())
            .orElseThrow(() -> new IllegalArgumentException("Conta " + data.accountId() + " não encontrada"));
       
         if (data.type() == TypeTransaction.EXPENSE && account.getBalance().compareTo(data.amount())<0){
        
                throw new IllegalArgumentException("Saldo insuficiente para realizar a transação");
            }
           
    

}}