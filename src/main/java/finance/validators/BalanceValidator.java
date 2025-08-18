package finance.validators;

import finance.domain.acounts.Account;
import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryUser;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BalanceValidator implements ValidatorTransaction {

    @Autowired
    RepositoryAccount repositoryAccount;
    @Autowired
    RepositoryUser repositoryUser;

    @Override
    public void validate(TransactionCreateDTO data) {




    }



}
