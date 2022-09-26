package com.thanksto.backthanksto.user.dao;

import com.thanksto.backthanksto.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
