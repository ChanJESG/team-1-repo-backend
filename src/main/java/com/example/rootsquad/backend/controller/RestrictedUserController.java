package com.example.rootsquad.backend.controller;

import com.example.rootsquad.backend.dto.UserDto;
import com.example.rootsquad.backend.exception.ResourceNotFoundException;
import com.example.rootsquad.backend.model.User;
import com.example.rootsquad.backend.service.AuthService;
import com.example.rootsquad.backend.service.PostServiceInterface;
import com.example.rootsquad.backend.service.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
// TODO change back to restricted except for add user
@RequestMapping("/public/api/user")
public class RestrictedUserController {

    @Autowired
    UserServiceInterface userService;
    @Autowired
    PostServiceInterface postService;
    @Autowired
    AuthService authService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/signup")
    public ResponseEntity<Object> addUser(@RequestParam("userData") String userData, @Nullable @RequestParam("image")MultipartFile image ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = objectMapper.readValue(userData, UserDto.class);

        User savedUser = new User();
        userDto.setUser(savedUser);

        /*savedUser.setUserName(userDto.getName());
        savedUser.setEmail(userDto.getEmail());
        savedUser.setPassword(userDto.getPassword());
        savedUser.setRole(userDto.getRole());*/
        authService.signUp(userDto);

        if (image!= null) {
            String fileName = "profile_" + System.currentTimeMillis()+ "_" + image.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;
            File imageFile = new File(filePath);
            image.transferTo(imageFile.toPath());

            savedUser.setUserProfileImage(filePath);
        }

        userService.save(savedUser);
        userDto.setPassword("");
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    // sign in a user
    @PostMapping("/signin")
    public ResponseEntity<UserDto> signIn(@Valid @RequestBody UserDto signInRequest) {
        UserDto signInResponse = authService.signIn(signInRequest);
        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }

    // update a user
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestParam("userData") String userData, @Nullable @RequestParam("image")MultipartFile image) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = objectMapper.readValue(userData, UserDto.class);

        User updatedUser = userService.findById(id).orElseThrow(()-> new ResourceNotFoundException());

        updatedUser.setUserName(userDto.getName());
        updatedUser.setEmail(userDto.getEmail());
        updatedUser.setPassword(userDto.getPassword());
        updatedUser.setUserBio(userDto.getUserBio());

        if (image!= null) {
            String fileName = System.currentTimeMillis()+ "_" + image.getOriginalFilename() + "_" + "profile";
            String filePath = uploadDir + File.separator + fileName;
            File imageFile = new File(filePath);
            image.transferTo(imageFile.toPath());

            updatedUser.setUserProfileImage(filePath);
        }

        return new ResponseEntity<>(userService.save(updatedUser), HttpStatus.OK);
    }

    // getting all users
    @GetMapping
    public ResponseEntity<Object> getUsers() {
        List<User> userList = userService.findAll();

        if (userList.isEmpty())
            throw new ResourceNotFoundException();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // get user by email
    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Long id) {
        User deletedUser = userService.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        userService.delete(id);

        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}

























