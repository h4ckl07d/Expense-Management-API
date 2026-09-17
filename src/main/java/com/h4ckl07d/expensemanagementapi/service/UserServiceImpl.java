package com.h4ckl07d.expensemanagementapi.service;


import com.h4ckl07d.expensemanagementapi.Exception.EmailAlreadyExistsException;
import com.h4ckl07d.expensemanagementapi.Exception.UserNotFoundException;
import com.h4ckl07d.expensemanagementapi.dto.request.CreateUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.request.LoginUserRequest;
import com.h4ckl07d.expensemanagementapi.dto.response.LoginResponse;
import com.h4ckl07d.expensemanagementapi.dto.response.UserResponse;
import com.h4ckl07d.expensemanagementapi.entity.User;
import com.h4ckl07d.expensemanagementapi.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    public final AuthenticationManager authenticationManager;
    public final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());

        String hashedPassword = passwordEncoder.encode(request.password());

        user.setPassword(hashedPassword);

        User response = userRepository.save(user);
        return UserResponse.from(response);
    }
;
    @Override
    public LoginResponse login(LoginUserRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String token = jwtService.generateToken(email);

        return LoginResponse.from(user, token);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User response = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserResponse.from(response);
    }
}
