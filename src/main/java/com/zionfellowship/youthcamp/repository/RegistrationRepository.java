package com.zionfellowship.youthcamp.repository;

import com.zionfellowship.youthcamp.entity.Registration;
import com.zionfellowship.youthcamp.enums.CampGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.Optional;

public interface RegistrationRepository
        extends JpaRepository<Registration, Long> {

    Optional<Registration> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    Page<Registration>
    findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneNumberContaining(
            String name,
            String email,
            String phoneNumber,
            Pageable pageable
    );

    Page<Registration> findByGroup(
            CampGroup group,
            Pageable pageable
    );

    Page<Registration>
    findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneNumberContainingAndGroup(
            String name,
            String email,
            String phoneNumber,
            CampGroup group,
            Pageable pageable
    );

    long countByGroup(
            CampGroup group
    );

    long countByCreatedAtGreaterThanEqual(
            Instant startOfDay
    );
}