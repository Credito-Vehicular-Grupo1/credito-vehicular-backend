package com.grupo1.creditovehicular.user.domain.persistence;

import com.grupo1.creditovehicular.user.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByFirstName(String firstName);
    User findByEmailAndPassword(String email, String password);
}
