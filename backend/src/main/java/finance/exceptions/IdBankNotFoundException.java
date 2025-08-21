package finance.exceptions;

public class IdBankNotFoundException extends RuntimeException{
    public IdBankNotFoundException(Long id) {
        super("ID " + id + " not found.");

    }

}
