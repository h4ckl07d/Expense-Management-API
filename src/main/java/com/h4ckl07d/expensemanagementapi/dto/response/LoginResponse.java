package com.h4ckl07d.expensemanagementapi.dto.response;

import com.h4ckl07d.expensemanagementapi.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.Authentication;

public record LoginResponse(
        String token,
        Long id,
        String email

) {
    public static LoginResponse from(User user, String token) {
        return new LoginResponse(
                token,
                user.getId(),
                user.getEmail()

        );
    }
}


