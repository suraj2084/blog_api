package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Exceptions.ResourceNotFoundException;
import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import com.suraj.blog_api.surajblogapi.Repository.UserRepo;
import com.suraj.blog_api.surajblogapi.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public ApiResponse createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        userRepo.save(user);
        return new ApiResponse("User Created successfully", true);
    }

    @Override
    public ApiResponse updateUser(UserDto userDto, int user_id) {
        // First Finding user
        User user = userRepo.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        userRepo.save(user);
        return new ApiResponse("User Updated successfully", true);
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
    public ApiResponse deleteByID(int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        userRepo.delete(user);

        return new ApiResponse("User deleted successfully", true);

    }

    @Override
    public ApiResponse deleteAllUser() {
        userRepo.deleteAll();
        return new ApiResponse("Users deleted successfully", true);
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
