package finance.services;

import finance.domain.acounts.Account;
import finance.domain.transactions.Transaction;
import finance.domain.transactions.TypeTransaction;
import finance.domain.user.User;
import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryTransactions;
import finance.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ServiceTransactions {

    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryTransactions repositoryTransactions;
    @Autowired
    private RepositoryAccount repositoryAccount;

    @Transactional
    public void createTransaction(TransactionCreateDTO data) {


        User user = repositoryUser.findById(data.userId()).
                orElseThrow(() -> new IllegalArgumentException("Usuário" + data.userId() + " não encontrado"));
        Account account = repositoryAccount.findById(data.accountId()).
                orElseThrow(() -> new IllegalArgumentException("Conta " + data.accountId() + " não encontrada"));

        var transaction = new Transaction(user, account, data.category(), data.name().trim(), data.type(), data.amount());
        if (data.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero.");
        }
        if (data.type().equals(TypeTransaction.fromString("EXPENSE"))){
            account.setBalance(account.getBalance().subtract(data.amount()));
            repositoryAccount.save(account);
        }
        if (data.type().equals(TypeTransaction.INCOME)) {
            account.setBalance(account.getBalance().add(data.amount()));
            repositoryAccount.save(account);
        }


        repositoryTransactions.save(transaction);
    }

    @Transactional
    public void receiveTransaction(TransactionCreateDTO data) {
        User user = repositoryUser.findById(data.userId()).
                orElseThrow(() -> new IllegalArgumentException("Usuário" + data.userId() + " não encontrado"));
        Account account = repositoryAccount.findById(data.accountId()).
                orElseThrow(() -> new IllegalArgumentException("Conta " + data.accountId() + " não encontrada"));
        var transaction = new Transaction(user, account, data.category(), data.name().trim(), data.type(), data.amount());
    }
}