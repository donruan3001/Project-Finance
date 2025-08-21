package finance.validators.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finance.dto.transactions.TransactionCreateDTO;

@Component
public class CompositeTransactionValidator implements ValidatorTransaction {
    
    
    private final List<ValidatorTransaction> validators;
    
    @Autowired
    public CompositeTransactionValidator(List<ValidatorTransaction> validators){
        this.validators=validators;
    }

    @Override
    public void validate(TransactionCreateDTO data) {
        for(ValidatorTransaction validator: validators){
            validator.validate(data);
        }
    }
}
