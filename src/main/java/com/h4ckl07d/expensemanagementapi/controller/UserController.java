package com.h4ckl07d.expensemanagementapi.controller;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.request.UpdateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.UserResponse;
import com.h4ckl07d.expensemanagementapi.entity.User;
import com.h4ckl07d.expensemanagementapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody CreateUserRequest request){
        User user = userService.createUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }
    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserDetails(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request, Authentication authentication
    ){
        User currentUser = (User) authentication.getPrincipal(); // TODO: confirm once auth is implemented
        User updatedUser = userService.updateUser(id, request, currentUser);
        return ResponseEntity.ok(updatedUser);
    }
}
