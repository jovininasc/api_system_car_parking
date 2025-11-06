package com.systemcars.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.systemcars.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}