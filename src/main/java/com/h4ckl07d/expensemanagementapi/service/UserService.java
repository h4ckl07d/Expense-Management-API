package com.h4ckl07d.expensemanagementapi.service;

import com.h4ckl07d.expensemanagementapi.dto.request.CreateUserRequest;
import com.h4ckl07d.expensemanagementapi.entity.User;
import com.h4ckl07d.expensemanagementapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest request){
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    public User getAllUserById(Long id){
       return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "Not found"));
    }

}
