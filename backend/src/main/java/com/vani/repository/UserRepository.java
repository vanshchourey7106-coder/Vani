package com.vani.repository;

import com.vani.entity.User;
import com.vani.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

    long countByRole(Role role);
}