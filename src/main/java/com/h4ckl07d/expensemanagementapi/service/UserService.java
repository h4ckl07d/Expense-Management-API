package com.h4ckl07d.expensemanagementapi.service;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.request.LoginUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.LoginResponse;
import com.h4ckl07d.expensemanagementapi.dto.response.UserResponse;


public interface UserService {


    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);

    LoginResponse login(LoginUserRequest request);


}
