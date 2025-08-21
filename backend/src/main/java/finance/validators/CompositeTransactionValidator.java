package finance.validators;

import finance.dto.transactions.TransactionCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompositeTransactionValidator implements ValidatorTransaction {

   private List<ValidatorTransaction> validators;

   @Autowired
   public CompositeTransactionValidator(List<ValidatorTransaction> validators) {
       this.validators = validators;
   }

    @Override
    public void validate(TransactionCreateDTO transactionCreateDTO) {
        for (ValidatorTransaction validatorTransaction : validators) {
            validatorTransaction.validate(transactionCreateDTO);
        }
    }
}
