package com.gft.gdesk.controller;


import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.service.UserModelLoginService;
import com.gft.gdesk.service.UserModelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequestMapping("/api/v1/user")
@RestController

public class UserModelController {

    private final UserModelService userService;
    private final UserModelLoginService userLoginService;

    public UserModelController(UserModelService userService, UserModelLoginService userLoginService) {

        this.userService = userService;
        this.userLoginService = userLoginService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel toRegister) {
        return userService.registerUser(toRegister);
    }

    @GetMapping("/all-users")
    public List<UserModel> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel toLogin) {
        return userLoginService.login(toLogin);
    }
}