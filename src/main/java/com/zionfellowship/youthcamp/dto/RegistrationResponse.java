package com.zionfellowship.youthcamp.dto;

import com.zionfellowship.youthcamp.enums.CampGroup;

import java.time.Instant;

public record RegistrationResponse(

        Long id,

        String name,

        String email,

        String phoneNumber,

        Integer age,

        String address,

        String occupation,

        CampGroup group,

        Instant createdAt,

        Instant updatedAt
) {
}