package finance.validators.transactions;

import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorUser implements Validator {
    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public void validate(TransactionCreateDTO data) {
     repositoryUser.findById(data.userId()).
         orElseThrow(()->new IllegalArgumentException("User"+data.userId()+" does not exist"));}
}

