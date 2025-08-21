package finance.validators;
import finance.dto.transactions.TransactionCreateDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountValidator implements ValidatorTransaction {

    @Override
    public void validate(TransactionCreateDTO data) {
        if (data.amount() == null || data.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero.");
        }
    }
}