package finance.exceptions;

public class UnatorizedExecption extends RuntimeException{
    public UnatorizedExecption(String userNotAuthenticated) {
        super  (userNotAuthenticated);
    }
}
