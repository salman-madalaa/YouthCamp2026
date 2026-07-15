package com.zionfellowship.youthcamp.dto;

import com.zionfellowship.youthcamp.enums.CampGroup;
import jakarta.validation.constraints.*;

public record RegistrationUpdateRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        @Size(max = 150, message = "Email must not exceed 150 characters")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Please provide a valid 10-digit Indian phone number"
        )
        String phoneNumber,

        @NotNull(message = "Age is required")
        @Min(value = 13, message = "Age must be at least 13")
        @Max(value = 100, message = "Please provide a valid age")
        Integer age,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "Occupation is required")
        @Size(max = 100, message = "Occupation must not exceed 100 characters")
        String occupation,

        @NotNull(message = "Group is required")
        CampGroup group
) {
}