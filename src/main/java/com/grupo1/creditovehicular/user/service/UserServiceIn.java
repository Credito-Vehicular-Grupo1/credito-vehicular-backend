package com.grupo1.creditovehicular.user.service;

import com.grupo1.creditovehicular.shared.exception.ResourceNotFoundException;
import com.grupo1.creditovehicular.shared.exception.ResourceValidationException;
import com.grupo1.creditovehicular.user.domain.model.entity.User;
import com.grupo1.creditovehicular.user.domain.persistence.UserRepository;
import com.grupo1.creditovehicular.user.domain.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceIn implements UserService {
    private static final String ENTITY = "User";

    private final UserRepository userRepository;
    private final Validator validator;

    public UserServiceIn(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User create(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User userWithEmail = userRepository.findByEmail(user.getEmail());

        if (userWithEmail != null)
            throw new ResourceValidationException(ENTITY,
                    "An user with the same email already exists.");

        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User request) {
        Set<ConstraintViolation<User>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user ->
                userRepository.save(user.withFirstName(request.getFirstName())
                        .withLastName(request.getLastName())
                        .withEmail(request.getLastName())
                        .withPassword(request.getPassword())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User getInfoUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(
                user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
