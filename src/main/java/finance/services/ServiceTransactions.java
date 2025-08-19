package finance.services;

import java.math.BigDecimal;
import java.util.List;

import finance.validators.transactions.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.domain.acounts.Account;
import finance.domain.transactions.Transaction;
import finance.domain.transactions.TypeTransaction;
import finance.domain.user.User;
import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryTransactions;
import finance.repository.RepositoryUser;
import jakarta.transaction.Transactional;

@Service
public class ServiceTransactions {

    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryTransactions repositoryTransactions;
    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private List<Validator> validators;

    @Transactional
    public void createTransaction(TransactionCreateDTO data) {
        User user = repositoryUser.getReferenceById(data.userId());
        Account account = repositoryAccount.getReferenceById(data.accountId());

        validators.forEach(validator -> validator.validate(data));

        //cria o um objeto Transaction
        var transaction = new Transaction(user, account, data.category(), data.name().trim(), data.type(), data.amount());
       // Atualiza o saldo da conta
        if (data.type().equals(TypeTransaction.EXPENSE)){
            account.setBalance(account.getBalance().subtract(data.amount()));
            repositoryAccount.save(account);
        }
        // Se for uma transação de entrada, adiciona o valor ao saldo da conta
        if (data.type().equals(TypeTransaction.INCOME)) {
            account.setBalance(account.getBalance().add(data.amount()));
            repositoryAccount.save(account);
        }

        repositoryTransactions.save(transaction);
    }}


