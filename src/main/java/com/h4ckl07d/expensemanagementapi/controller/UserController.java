package com.h4ckl07d.expensemanagementapi.controller;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.request.LoginUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.UserResponse;
import com.h4ckl07d.expensemanagementapi.service.JwtService;
import com.h4ckl07d.expensemanagementapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request){
        UserResponse response = userService.createUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/auth/login")
    public  ResponseEntity<String> login(
           @Valid @RequestBody LoginUserRequest request
            ){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        String token = jwtService.generateToken(authentication.getName());
        return ResponseEntity.ok(token);
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
