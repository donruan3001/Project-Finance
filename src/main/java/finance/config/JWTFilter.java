package finance.config;

import finance.domain.user.User;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import finance.repository.RepositoryUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
@Autowired
    private JWTService jwtService;
@Autowired
    private RepositoryUser userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    var token=getToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        // Busca o usuário no repositório
     if (token != null) {
            var subject = jwtService.getSecretKey(token);
            UserDetails usuario = userRepository.findByUsername(subject);

            if (usuario != null) {
                var authentication = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }




        
    }
    private String getToken(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }

}
