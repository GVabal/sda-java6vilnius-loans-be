package dev.vabalas.loans.security;

import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenProperties tokenProperties;
    private final UserDetailsService userDetailsService;
    private final TokenParser tokenParser;
    private final TokenValidator tokenValidator;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String authTokenWithPrefix = httpRequest.getHeader(tokenProperties.getHeader());
        String authToken;
        if (authTokenWithPrefix != null) {
            authToken = tokenParser.removePrefix(authTokenWithPrefix);
        } else {
            authToken = null;
        }
        String email = tokenParser.parseEmail(authToken);
        if (authToken != null
                && email != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                User user = (User) userDetailsService.loadUserByUsername(email);
                if (tokenValidator.isValid(authToken, TokenType.ACCESS_TOKEN, user)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (UsernameNotFoundException ex) {
                //
            }
        }
        chain.doFilter(req, res);
    }
}
