package com.suraj.blog_api.surajblogapi.Services;

import org.springframework.stereotype.Service;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import java.util.List;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, int user_id);

    UserDto getUserById(int id);

    List<UserDto> getAllUser();

    void deleteByID(int id);

    void deleteAllUser();

}
