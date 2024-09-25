package com.suraj.blog_api.surajblogapi.Services;

import org.springframework.stereotype.Service;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import java.util.*;

@Service
public interface UserService {

    ApiResponse createUser(UserDto userDto);

    ApiResponse updateUser(UserDto userDto, int user_id);

    UserDto getUserById(int id);

    List<UserDto> getAllUser();

    ApiResponse deleteByID(int id);

    ApiResponse deleteAllUser();

}
