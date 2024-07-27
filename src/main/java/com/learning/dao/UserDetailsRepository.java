package com.learning.dao;

import com.learning.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity,Long> {
    Optional<UserDetailsEntity> findByUserName(String userName);
}
