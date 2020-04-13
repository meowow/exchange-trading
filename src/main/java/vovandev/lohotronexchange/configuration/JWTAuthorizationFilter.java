package vovandev.lohotronexchange.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import vovandev.lohotronexchange.entity.User;
import vovandev.lohotronexchange.model.UserPrincipal;
import vovandev.lohotronexchange.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTConfigurationConstants.AUTH_HEADER);
        if(header == null || !header.startsWith(JWTConfigurationConstants.BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getAuth(request, header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication getAuth(HttpServletRequest request, String header) {
        String userName = JWT.require(Algorithm.HMAC512(JWTConfigurationConstants.SECRET.getBytes()))
                .build()
                .verify(header.replace(JWTConfigurationConstants.BEARER, ""))
                .getSubject();

        if (userName == null) return null;
        User user = userRepository.findByUsername(userName);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return new UsernamePasswordAuthenticationToken(userName, null, userPrincipal.getAuthorities());
    }
}
