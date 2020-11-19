package dev.vabalas.loans.controller;

import dev.vabalas.loans.entity.Role;
import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.enums.RoleAuthority;
import dev.vabalas.loans.enums.TokenType;
import dev.vabalas.loans.exception.UnauthorizedException;
import dev.vabalas.loans.exception.UserExistsException;
import dev.vabalas.loans.payload.request.LoginRequest;
import dev.vabalas.loans.payload.request.RefreshTokenRequest;
import dev.vabalas.loans.payload.request.UserCreateRequest;
import dev.vabalas.loans.payload.response.AuthResponse;
import dev.vabalas.loans.payload.response.UserResponse;
import dev.vabalas.loans.security.TokenGenerator;
import dev.vabalas.loans.security.TokenParser;
import dev.vabalas.loans.security.TokenValidator;
import dev.vabalas.loans.service.RoleService;
import dev.vabalas.loans.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final TokenGenerator tokenGenerator;
    private final TokenParser tokenParser;
    private final TokenValidator tokenValidator;

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        if (userService.existsByEmail(userCreateRequest.getEmail())) {
            throw new UserExistsException(
                    String.format("User with email %s already exists", userCreateRequest.getEmail()));
        }
        if (userService.existsByUsername(userCreateRequest.getUsername())) {
            throw new UserExistsException(
                    String.format("User with username %s already exists", userCreateRequest.getUsername()));
        }
        Role role = roleService.mustFindByName(RoleAuthority.ROLE_CUSTOMER);
        User user =
                userCreateRequest.asUser(passwordEncoder.encode(userCreateRequest.getPassword()), role);
        userService.save(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) userDetailsService.loadUserByUsername(loginRequest.getEmail());
        return generateAuthResponse(user);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = tokenParser.removePrefix(refreshTokenRequest.getRefreshToken());
        String email = tokenParser.parseEmail(refreshToken);
        if (refreshToken == null || email == null) {
            throw new UnauthorizedException("Refresh token is expired or invalid");
        }
        User user = (User) userDetailsService.loadUserByUsername(email);
        if (!tokenValidator.isValid(refreshToken, TokenType.REFRESH_TOKEN, user)) {
            throw new UnauthorizedException("Refresh token is expired or invalid");
        }
        return generateAuthResponse(user);
    }

    private AuthResponse generateAuthResponse(User user) {
        String accessToken = tokenGenerator.generate(user, TokenType.ACCESS_TOKEN, 3600);
        String refreshToken = tokenGenerator.generate(user, TokenType.REFRESH_TOKEN, 7200);
        return new AuthResponse(accessToken, refreshToken, UserResponse.fromUser(user));
    }
}
