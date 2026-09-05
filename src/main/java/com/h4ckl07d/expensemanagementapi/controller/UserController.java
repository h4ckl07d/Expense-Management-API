package com.h4ckl07d.expensemanagementapi.controller;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.request.UpdateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.UserResponse;
import com.h4ckl07d.expensemanagementapi.entity.User;
import com.h4ckl07d.expensemanagementapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request){
        UserResponse response = userService.createUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("users/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id){
        UserResponse response = userService.getUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUserDetails(
//            @PathVariable Long id,
//            @Valid @RequestBody UpdateUserRequest request
//    ){
////        User currentUser = (User) authentication.getPrincipal(); // TODO: confirm once auth is implemented
//        User updatedUser = userService.updateUser(id, request);
//        return ResponseEntity.ok(updatedUser);
//    }
}
