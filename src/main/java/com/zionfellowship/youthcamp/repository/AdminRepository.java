package com.zionfellowship.youthcamp.repository;

import com.zionfellowship.youthcamp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository
        extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
}