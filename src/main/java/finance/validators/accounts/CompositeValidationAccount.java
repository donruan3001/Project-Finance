package finance.validators.accounts;


import finance.dto.accounts.AccountCreateDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompositeValidationAccount implements ValidationAccount {
    @Override
    public void validate(AccountCreateDTO data) {
        validators.forEach(validators -> validators.validate(data));
    }

    final List<ValidationAccount> validators;


    public CompositeValidationAccount(List<ValidationAccount> validators) {
        this.validators = validators;


    }
}
