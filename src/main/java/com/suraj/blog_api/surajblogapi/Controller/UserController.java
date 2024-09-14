package com.suraj.blog_api.surajblogapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blog_api.surajblogapi.Entities.User;
import com.suraj.blog_api.surajblogapi.Payloads.UserDto;
import com.suraj.blog_api.surajblogapi.Services.UserService;

//For Api declare @RestController
@RestController()
@RequestMapping("/api/user")
public class UserController {

    /*Now we will use a UserServices method.*/
    @Autowired
    private UserService userService;
    //Post Create usser


    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
       UserDto creaUserDto= userService.createUser(null);
       return new ResponseEntity<>(creaUserDto, HttpStatus.CREATED);
    }
    //PUT Update User


    //Get Fetch User


    // Delete delete a user
    
}
