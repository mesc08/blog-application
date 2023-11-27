package com.project.bloggingapp.controllers;

import com.project.bloggingapp.utils.AppConstants;
import com.project.bloggingapp.payloads.ApiResponse;
import com.project.bloggingapp.payloads.UserDto;
import com.project.bloggingapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return ResponseEntity.ok(new ApiResponse(AppConstants.CREATED_STATUS_CODE, AppConstants.USER_ADDED_SUCCESS, true, createdUserDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto updatedUser = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(new ApiResponse(AppConstants.OK_STATUS_CODE, String.format(AppConstants.USER_UPDATED_SUCCESS, uid), true, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return ResponseEntity.ok(new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.USER_DELETED_SUCCESS, true, null));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllUsers(){
        List<UserDto> allUserDto = this.userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.USER_ALL_SUCCESS, false, allUserDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getSingleUser(@PathVariable("userId") Integer uid){
        UserDto singleuser = this.userService.getUserById(uid);
        return ResponseEntity.ok(new ApiResponse(AppConstants.OK_STATUS_CODE, String.format(AppConstants.USER_GET_SUCCESS, uid), false, singleuser));
    }
}
