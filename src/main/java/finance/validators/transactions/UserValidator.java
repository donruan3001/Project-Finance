package finance.validators.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finance.dto.transactions.TransactionCreateDTO;
import finance.repository.RepositoryUser;

@Component
public class UserValidator implements ValidatorTransaction {

    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public void validate(TransactionCreateDTO data) {
        if (data.userId() == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório");
        }
        
        repositoryUser.findById(data.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário " + data.userId() + " não encontrado"));
    }
    
   
}
