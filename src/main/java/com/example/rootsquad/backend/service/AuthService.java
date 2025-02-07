package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.dto.UserDto;
import com.example.rootsquad.backend.exception.PasswordBlankException;
import com.example.rootsquad.backend.model.User;
import com.example.rootsquad.backend.repository.UserRepository;
import com.example.rootsquad.backend.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    public UserDto signUp(UserDto signUpRequest) {
        User user = signUpRequest.getUser();

        user.setUserName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());

        if (signUpRequest.getPassword().isBlank())
            throw new PasswordBlankException();
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(signUpRequest.getRole());

        return signUpRequest;
    }

    public UserDto signIn(UserDto signInRequest) {
        UserDto requestResponse = new UserDto();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow();
        var jwt = jwtUtils.generateToken(user);
        var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

        requestResponse.setToken(jwt);
        requestResponse.setRefreshToken(refreshToken);
        requestResponse.setExpirationTime("24Hr");
        requestResponse.setMessage("Signed in successfully.");

        return requestResponse;
    }

}
