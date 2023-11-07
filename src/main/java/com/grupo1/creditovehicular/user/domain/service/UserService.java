package com.grupo1.creditovehicular.user.domain.service;

import com.grupo1.creditovehicular.user.domain.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getAll();
    Page<User> getAll(Pageable pageable);
    User getByEmail(String email);
    User create(User user);
    User update(Long userId, User user);
    User getInfoUserById(Long userId);
    ResponseEntity<?> delete(Long userId);

}
