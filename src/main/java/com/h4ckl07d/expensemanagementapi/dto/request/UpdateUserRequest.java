package com.h4ckl07d.expensemanagementapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank
        @Size(max = 100, message = "Name must not exceed 100 characters ")
        String name,

        @NotBlank
        @Size(max = 250, message = "Email must not exceed 250 characters ")
        String email
) {}
