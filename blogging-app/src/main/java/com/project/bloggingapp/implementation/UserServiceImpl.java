package com.project.bloggingapp.implementation;

import com.project.bloggingapp.entities.User;
import com.project.bloggingapp.exceptions.ResourceNotFoundException;
import com.project.bloggingapp.payloads.UserDto;
import com.project.bloggingapp.repositories.UserRepository;
import com.project.bloggingapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
        User newUser = this.modelMapper.map(user, User.class);
        User savedUser = this.userRepository.save(newUser);
        UserDto respuser = this.modelMapper.map(savedUser, UserDto.class);
        return respuser;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
           "User", " Id ", userId
        ));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User editUser = this.userRepository.save(user);
        UserDto updatedUserDto = this.modelMapper.map(editUser, UserDto.class);
        return updatedUserDto;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User", " Id ", userId
        ));
        UserDto foundUserDto = this.modelMapper.map(user, UserDto.class);
        return foundUserDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user ->
            this.modelMapper.map(user, UserDto.class)
        ).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User", " Id ", userId
        ));
        this.userRepository.delete(user);
    }
}
