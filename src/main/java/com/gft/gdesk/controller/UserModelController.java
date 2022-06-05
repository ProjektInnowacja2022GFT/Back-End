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

    private final UserModelService userModelService;
    private final UserModelLoginService userModelLoginService;

    public UserModelController(UserModelService userService, UserModelLoginService userLoginService) {

        this.userModelService = userService;
        this.userModelLoginService = userLoginService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel toRegister) {
        return userModelService.registerUser(toRegister);
    }

    @GetMapping("/all-users")
    public List<UserModel> getUsers() {
        return userModelService.getAllUsers();
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel toLogin) {
        return userModelLoginService.login(toLogin);
    }

    @GetMapping("/wait-for-approval-users")
    public List<UserModel> getWaitForApprovalUsers() {
        return userModelService.getWaitForApprovalUsers();
    }
}