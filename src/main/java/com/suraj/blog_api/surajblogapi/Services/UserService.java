package com.suraj.blog_api.surajblogapi.Services;

import org.springframework.stereotype.Service;

import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import java.util.*;

@Service
public interface UserService {

    ApiResponse createUser(UserDto userDto);

    ApiResponse updateUser(UserDto userDto, Integer user_id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUser();

    ApiResponse deleteByID(Integer id);

    ApiResponse deleteAllUser();

    String verify(UserDto userDto);

}
