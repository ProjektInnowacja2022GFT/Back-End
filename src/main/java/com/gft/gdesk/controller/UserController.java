package com.gft.gdesk.controller;


import com.gft.gdesk.dto.User;
import com.gft.gdesk.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequestMapping("/api/v1/users")
@RestController

public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}