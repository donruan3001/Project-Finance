package finance.validators.transactions;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import finance.dto.transactions.TransactionCreateDTO;

@Component
public class BasicDataValidator implements ValidatorTransaction {

    @Override
    public void validate(TransactionCreateDTO data) {
        // Validar campos obrigatórios
        if (data.name() == null || data.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da transação é obrigatório");
        }
        
        if (data.type() == null) {
            throw new IllegalArgumentException("Tipo da transação é obrigatório");
        }
        
        if (data.category() == null) {
            throw new IllegalArgumentException("Categoria da transação é obrigatória");
        }
        
        if (data.amount() == null) {
            throw new IllegalArgumentException("Valor da transação é obrigatório");
        }
        
        // Validar valor positivo
        if (data.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero");
        }
        
        // Validar comprimento do nome
        if (data.name().trim().length() > 100) {
            throw new IllegalArgumentException("Nome da transação não pode ter mais de 100 caracteres");
        }
    }

} 