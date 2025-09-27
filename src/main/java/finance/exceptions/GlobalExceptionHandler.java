package finance.exceptions;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = Logger.
            getLogger(GlobalExceptionHandler.class.getName());


    //Trata exception 404 not found
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String,Object>>handlerUserNotFoundException(UsernameNotFoundException  e) {
        logger.warning("User not found exception: " + e);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "User Not Found");
        response.put("message", e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    //Trata exception 400 bad request
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Map<String,Object>> handlerInvalidInputException(InvalidParameterException e) {
        logger.warning("Invalid input exception: " + e);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Invalid Input");
        response.put("message", e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    //trata exceptions 403 forbidden
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String,Object>>handlerAccessDeniedException(AccessDeniedException e) {
        logger.warning("Access denied exception: " + e);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.FORBIDDEN.value());
        response.put("error", "Access Denied");
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    //trata exceptions 404 not found
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e) {
        logger.warning("Erro de validação: "+e);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Erro de validação");
        response.put("message", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    //trata exceptions 500 internal server error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        logger.warning("Erro inesperado: "+e);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Erro interno do servidor");
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //trata exceptions 400 bad request
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.warning("Illegal argument exception: " + e);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Invalid Argument");
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ProblemDetail handleEmail(EmailAlreadyExistsException ex) {
    var pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    pd.setTitle("Email já cadastrado");
    pd.setDetail(ex.getMessage());
    return pd;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ProblemDetail handleNotFound(EntityNotFoundException ex) {
    var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    pd.setTitle("Recurso não encontrado");
    pd.setDetail(ex.getMessage());
    return pd;
  }
}

