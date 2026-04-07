package com.wegoauthentic.app.repository;

import com.wegoauthentic.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByPhone(String phone);
}
