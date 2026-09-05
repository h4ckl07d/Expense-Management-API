package com.h4ckl07d.expensemanagementapi.service;


import com.h4ckl07d.expensemanagementapi.dto.request.CreateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.UserResponse;
import com.h4ckl07d.expensemanagementapi.entity.User;
import com.h4ckl07d.expensemanagementapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());

        String hashedPassword = passwordEncoder.encode(request.password());

        user.setPassword(hashedPassword);

        User response = userRepository.save(user);
        return UserResponse.from(response);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User response = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserResponse.from(response);
    }
}
