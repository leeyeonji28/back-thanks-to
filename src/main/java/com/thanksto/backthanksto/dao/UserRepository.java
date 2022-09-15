package com.thanksto.backthanksto.dao;

import com.thanksto.backthanksto.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
