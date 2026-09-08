package com.h4ckl07d.expensemanagementapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserRequest(
        @NotBlank
        @Email(message = "Email is Required")
        @Size(max = 255, message = "Email must not exceed 255 characters")
        String email,

        @NotBlank
        String password
) {}
