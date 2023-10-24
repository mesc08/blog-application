package com.project.bloggingapp.controllers;

import com.project.bloggingapp.entities.User;
import com.project.bloggingapp.payloads.ApiResponse;
import com.project.bloggingapp.payloads.UserDto;
import com.project.bloggingapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return ResponseEntity.ok(new ApiResponse(202, "User got created successfully", true, createdUserDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto updatedUser = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(new ApiResponse(200, String.format("User updated with %d succesfully ", uid), true, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return ResponseEntity.ok(new ApiResponse(200, "User Deleted Successfully", true, null));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllUsers(){
        List<UserDto> allUserDto = this.userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse(200, "Found all users", false, allUserDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getSingleUser(@PathVariable("userId") Integer uid){
        UserDto singleuser = this.userService.getUserById(uid);
        return ResponseEntity.ok(new ApiResponse(200, String.format("Found user by %d id", uid), false, singleuser));
    }
}
