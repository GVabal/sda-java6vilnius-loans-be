package dev.vabalas.loans.service;

import dev.vabalas.loans.entity.User;
import dev.vabalas.loans.exception.NotFoundException;
import dev.vabalas.loans.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public User findByEmail(String email) {
        LOGGER.info("findByEmail({})", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email));
    }

    public void save(User user) {
        LOGGER.info("save({})", user);
        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        LOGGER.info("existsByEmail({})", email);
        return userRepository.existsByEmail(email);
    }
}
