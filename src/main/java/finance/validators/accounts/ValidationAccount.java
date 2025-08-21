package finance.validators.accounts;

import finance.dto.accounts.AccountCreateDTO;

public interface ValidationAccount {
    void validate(AccountCreateDTO data);
}
