package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Exceptions.ResourceNotFoundException;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import com.suraj.blog_api.surajblogapi.Repository.UserRepo;
import com.suraj.blog_api.surajblogapi.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto udateUser(UserDto userDto, int user_id) {
        // First Finding user
        User user = userRepo.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(int user_id) {
        User user = userRepo.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> list = this.userRepo.findAll();
        List<UserDto> userDtos = list.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;

    }

    @Override
    public void deleteByID(int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        userRepo.delete(user);
    }

    @Override
    public void deleteAllUser() {
        userRepo.deleteAll();
    }

    private User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }

}
