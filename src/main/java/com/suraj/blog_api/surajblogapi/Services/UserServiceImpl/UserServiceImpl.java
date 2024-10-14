package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Exceptions.ResourceNotFoundException;
import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import com.suraj.blog_api.surajblogapi.Repository.UserRepo;
import com.suraj.blog_api.surajblogapi.Services.JWTServices;
import com.suraj.blog_api.surajblogapi.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authmager;

    @Autowired
    JWTServices jwtServices;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public ApiResponse createUser(UserDto userDto) {

        // User user = this.dtoToUser(userDto);
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
        return new ApiResponse("User Created successfully", true);
    }

    @Override
    public ApiResponse updateUser(UserDto userDto, Integer user_id) {
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
    public UserDto getUserById(Integer user_id) {
        User user = userRepo.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        // return this.userToDto(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> list = this.userRepo.findAll();
        List<UserDto> userDtos = list.stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtos;

    }

    @Override
    public ApiResponse deleteByID(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        userRepo.delete(user);

        return new ApiResponse("User deleted successfully", true);

    }

    @Override
    public ApiResponse deleteAllUser() {
        userRepo.deleteAll();
        return new ApiResponse("Users deleted successfully", true);
    }

    @Override
    public String verify(UserDto user) {
        String Name = user.getName();
        String password = user.getPassword();
        Authentication authentication = authmager.authenticate(new UsernamePasswordAuthenticationToken(
                Name, password));

        if (authentication.isAuthenticated()) {
            return jwtServices.generateToken(Name);

        }
        return "Fail";
    }

}
