package finance.validators;

import finance.domain.acounts.Account;
import finance.domain.transactions.TypeTransaction;
import finance.domain.user.User;
import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class ValidatorUser implements ValidatorTransaction {
    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public void validate(TransactionCreateDTO data) {
     repositoryUser.findById(data.userId()).
         orElseThrow(()->new IllegalArgumentException("User"+data.userId()+" does not exist"));}
}

