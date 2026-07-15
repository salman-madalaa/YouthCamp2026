package com.zionfellowship.youthcamp.config;

import com.zionfellowship.youthcamp.entity.Admin;
import com.zionfellowship.youthcamp.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (adminRepository.findByUsername("admin").isEmpty()) {

            Admin admin = Admin.builder()
                    .username("admin")
                    .password(
                            passwordEncoder.encode("Admin@123")
                    )
                    .build();

            adminRepository.save(admin);

            System.out.println(
                    "✅ Default admin user created successfully"
            );

        } else {

            System.out.println(
                    "✅ Default admin user already exists"
            );
        }
    }
}