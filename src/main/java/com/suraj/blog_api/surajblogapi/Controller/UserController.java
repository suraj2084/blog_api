package com.suraj.blog_api.surajblogapi.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import com.suraj.blog_api.surajblogapi.Services.UserService;

import jakarta.validation.Valid;

//For Api declare @RestController
@RestController()
@RequestMapping("/api/users")
public class UserController {

    /* Now we will use a UserServices method. */
    @Autowired
    private UserService userService;

    // Post Create usser
    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.createUser(userDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // PUT Update User
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@Valid @PathVariable("userId") Integer userId,
            @RequestBody UserDto userDto) {
        ApiResponse updatedUserDto = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    // GET Fetch User
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userId) {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // GET Fetch User
    @GetMapping("/")
    public ResponseEntity<?> getAllUser() {
        List<UserDto> userDto = userService.getAllUser();
        // return new ResponseEntity<>(userDto, HttpStatus.OK);
        if (userDto.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("No Data Available", false), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // DELETE Delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
        ApiResponse apiResponse = userService.deleteByID(userId);
        if (apiResponse.isSuccess()) {
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

    }

    // DELETE Delete a user
    @DeleteMapping("/")
    public ResponseEntity<ApiResponse> deleteall() {
        ApiResponse apiResponse = userService.deleteAllUser();
        if (apiResponse.isSuccess()) {
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

    }

}
