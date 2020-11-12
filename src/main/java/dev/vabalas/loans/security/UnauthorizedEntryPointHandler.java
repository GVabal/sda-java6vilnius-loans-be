package dev.vabalas.loans.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UnauthorizedEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException)
            throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        Map<String, Object> resp = new HashMap<>();
        resp.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        resp.put("exception", authException.getClass().toString());
        resp.put("status", HttpStatus.UNAUTHORIZED.value());
        resp.put("message", authException.getMessage());
        resp.put("timestamp", System.currentTimeMillis());
        resp.put("path", request.getServletPath());
        new ObjectMapper().writeValue(response.getOutputStream(), resp);
    }
}
