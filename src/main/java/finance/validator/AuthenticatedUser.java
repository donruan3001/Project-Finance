package finance.validator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class AuthenticatedUser {
    public String getUsername(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String username= auth.getName();
        
        return username;
    }
}
