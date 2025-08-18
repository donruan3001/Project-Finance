package finance.validators;
import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator implements ValidatorTransaction {

    @Autowired
    private RepositoryAccount repositoryAccount;

    @Override
    public void validate(TransactionCreateDTO data) {
        repositoryAccount.findById(data.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Conta " + data.accountId() + " não encontrada"));
    }}