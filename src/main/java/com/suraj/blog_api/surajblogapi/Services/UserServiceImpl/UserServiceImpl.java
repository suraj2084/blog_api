package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import com.suraj.blog_api.surajblogapi.Repository.UserRepo;
import com.suraj.blog_api.surajblogapi.Services.UserService;

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
        User user = userRepo.findAllById(user_id);

        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    @Override
    public List<UserDto> getAllUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUser'");
    }

    @Override
    public void deleteByID(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByID'");
    }

    @Override
    public void deleteAllUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllUser'");
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
