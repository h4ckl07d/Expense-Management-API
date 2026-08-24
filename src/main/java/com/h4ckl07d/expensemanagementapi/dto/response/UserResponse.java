package com.h4ckl07d.expensemanagementapi.dto.response;

import com.h4ckl07d.expensemanagementapi.entity.User;

import java.time.LocalDate;

public record UserResponse(
    Long id,
    String name,
    String email,
    LocalDate createdAt
) {

    public static UserResponse from(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}

