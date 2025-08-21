package finance.validators.transactions;

import finance.dto.transactions.TransactionCreateDTO;

public interface ValidatorTransaction {

     void validate(TransactionCreateDTO data);

}
