package finance.services;
import finance.dto.transactions.TransactionResponseDTO;
import finance.exceptions.RuntimeUserNotAuthorized;
import finance.exceptions.TransactionValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import finance.domain.acounts.Account;
import finance.domain.transactions.Transaction;
import finance.domain.transactions.TypeTransaction;
import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryTransactions;

import jakarta.transaction.Transactional;

@Service
public class ServiceTransactions {

    @Autowired
    private  RepositoryTransactions repositoryTransactions;
    @Autowired
    private RepositoryAccount repositoryAccount;

@Transactional
public TransactionResponseDTO createTransaction(TransactionCreateDTO data) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username= auth.getName();

    Account account = repositoryAccount.findById(data.accountId())
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

    if (!account.getUser().getUsername().equals(username)) {
        throw new RuntimeUserNotAuthorized("A conta nao pertence ao user autenticado");
    }


    try {

        if (data.type().equals(TypeTransaction.EXPENSE)) {
            account.setBalance(account.getBalance().subtract(data.amount()));
            repositoryAccount.save(account);
        }
        if (data.type().equals(TypeTransaction.INCOME)) {
            account.setBalance(account.getBalance().add(data.amount()));
            repositoryAccount.save(account);
        }
        Transaction transaction = new Transaction(
                account,
                data.category(),
                data.name(),
                data.type(),
                data.amount()
        );


        repositoryTransactions.save(transaction);

        return new TransactionResponseDTO(
                    transaction.getId(),
                    account.getId(),
                    transaction.getCategory(),
                    transaction.getName(),
                    transaction.getAmount()
        );

    } catch (TransactionValidation e) {
        throw new TransactionValidation("erro no processamento da transacao");
    }


}}