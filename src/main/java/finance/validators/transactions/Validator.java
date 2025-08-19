package finance.validators.transactions;

import finance.dto.transactions.TransactionCreateDTO;

public interface Validator {

     void validate(TransactionCreateDTO data);

}
