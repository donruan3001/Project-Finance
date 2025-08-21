package finance.exceptions;

public class IdUserNotFoundException extends RuntimeException{
    public IdUserNotFoundException(Long id) {
        super("ID " + id + " not found.");
        
    }

}
