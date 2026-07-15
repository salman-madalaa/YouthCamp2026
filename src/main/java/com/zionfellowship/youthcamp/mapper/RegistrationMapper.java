package com.zionfellowship.youthcamp.mapper;

import com.zionfellowship.youthcamp.dto.RegistrationCreateRequest;
import com.zionfellowship.youthcamp.dto.RegistrationResponse;
import com.zionfellowship.youthcamp.entity.Registration;
import com.zionfellowship.youthcamp.dto.RegistrationUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public Registration toEntity(RegistrationCreateRequest request) {

        return Registration.builder()
                .name(request.name())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .age(request.age())
                .address(request.address())
                .occupation(request.occupation())
                .group(request.group())
                .build();
    }

    public RegistrationResponse toResponse(Registration registration) {

        return new RegistrationResponse(
                registration.getId(),
                registration.getName(),
                registration.getEmail(),
                registration.getPhoneNumber(),
                registration.getAge(),
                registration.getAddress(),
                registration.getOccupation(),
                registration.getGroup(),
                registration.getCreatedAt(),
                registration.getUpdatedAt()
        );
    }

    public void updateEntity(
            Registration registration,
            RegistrationUpdateRequest request
    ) {

        registration.setName(request.name());
        registration.setEmail(request.email());
        registration.setPhoneNumber(request.phoneNumber());
        registration.setAge(request.age());
        registration.setAddress(request.address());
        registration.setOccupation(request.occupation());
        registration.setGroup(request.group());
    }
}