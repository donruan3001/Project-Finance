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


        User user = repositoryUser.findById(data.userId()).
                orElseThrow(() -> new IllegalArgumentException("Usuário" + data.userId() + " não encontrado"));
        Account account = repositoryAccount.findById(data.accountId()).
                orElseThrow(() -> new IllegalArgumentException("Conta " + data.accountId() + " não encontrada"));

        validators.forEach(validator -> validator.validate(data));

        var transaction = new Transaction(user, account, data.category(), data.name().trim(), data.type(), data.amount());
       // Atualiza o saldo da conta
        if (data.type().equals(TypeTransaction.EXPENSE)){
            account.setBalance(account.getBalance().subtract(data.amount()));
            repositoryAccount.save(account);
        }

        if (data.type().equals(TypeTransaction.INCOME)) {
            account.setBalance(account.getBalance().add(data.amount()));
            repositoryAccount.save(account);
        }

        repositoryTransactions.save(transaction);
    }}


