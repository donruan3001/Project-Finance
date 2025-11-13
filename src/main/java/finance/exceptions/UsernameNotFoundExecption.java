package finance.exceptions;

public class UsernameNotFoundExecption extends RuntimeException{
    public UsernameNotFoundExecption(String message){
        super(message);
    }
}