package com.gft.gdesk.controller;


import com.gft.gdesk.dto.User;
import com.gft.gdesk.service.UserLoginService;
import com.gft.gdesk.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/api/v1/users")
@RestController

@Getter
@Setter

public class UserController {

    private UserService userService;
    public UserLoginService userLoginService;

    public UserController(UserService userService, UserLoginService userLoginService){
        this.userService = userService;
        this.userLoginService = userLoginService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public User login(@RequestBody User toLogin){
        return userLoginService.login(toLogin);
    }

}