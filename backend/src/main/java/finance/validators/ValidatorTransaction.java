package finance.validators;

import finance.dto.transactions.TransactionCreateDTO;

public interface ValidatorTransaction {
    void validate(TransactionCreateDTO transactionCreateDTO) ;

}
