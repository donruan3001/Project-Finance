package finance.validators.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finance.dto.transactions.TransactionCreateDTO;

@Component
public class CompositeTransactionValidator implements Validator {
    
    
    private final List<Validator> validators;
    
    @Autowired
    public CompositeTransactionValidator(List<Validator> validators){
        this.validators=validators;
    }

    @Override
    public void validate(TransactionCreateDTO data) {
        for(Validator validator: validators){
            validator.validate(data);
        }
    }
}
