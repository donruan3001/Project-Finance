package finance.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import finance.domain.acounts.Account;
import finance.domain.transactions.Transaction;
import finance.domain.transactions.TypeTransaction;
import finance.dto.transactions.TransactionCreateDTO;
import finance.dto.transactions.TransactionResponseDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryTransactions;
import finance.repository.RepositoryUser;
import finance.validator.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class ServiceTransactions {

    @Autowired
    private  RepositoryTransactions repositoryTransactions;
    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private AuthenticatedUser authenticatedUser;
@Transactional
public TransactionResponseDTO createTransaction(TransactionCreateDTO data) {

    String username = authenticatedUser.getUsername();
    Account account = repositoryAccount.findById(data.accountId())
            .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

    // BUG FIX: Verificar autorização e lançar exceção se usuário não for dono da conta
    if (!account.getUser().getUsername().equals(username)) {
        throw new AccessDeniedException("Você não tem permissão para acessar esta conta");
    }



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
    }


    public Page <TransactionResponseDTO> getMyTransactions(Pageable pageable) {
        String username=authenticatedUser.getUsername();

        Account account = repositoryAccount.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));


        Page<Transaction> transactions = repositoryTransactions.findByAccountId(account.getId(), pageable);
        return transactions.map(transaction -> new TransactionResponseDTO(
            transaction.getId(),
            transaction.getAccount().getId(),
            transaction.getCategory(),
            transaction.getName(),
            transaction.getAmount(),
            transaction.getCreated(),
            transaction.getUpdated()
        ));
    }

    public TransactionResponseDTO getTransactionById(Long id) {
        String username = authenticatedUser.getUsername();
        
        Transaction transaction = repositoryTransactions.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
        
        // CORREÇÃO DE SEGURANÇA: Verificar se o usuário é dono da conta da transação
        if (!transaction.getAccount().getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("Você não tem permissão para acessar esta transação");
        }
        
        return new TransactionResponseDTO(
            transaction.getId(),
            transaction.getAccount().getId(),
            transaction.getCategory(),
            transaction.getName(),
            transaction.getAmount(),
            transaction.getCreated(),
            transaction.getUpdated()
        );
    }
}