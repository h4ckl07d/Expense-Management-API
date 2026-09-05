package com.h4ckl07d.expensemanagementapi.service;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.UserResponse;


public interface UserService {


    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);


//    public User updateUser(Long id, UpdateUserRequest request){
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalStateException("User Not Found"));
////        if(!user.getId().equals(currentUser.getId())){
////            throw new IllegalStateException("Not Authorised to update user ");
////        }
//        user.setName(request.name());
//        user.setEmail(request.email());
//
//        return userRepository.save(user);
//    }

}
