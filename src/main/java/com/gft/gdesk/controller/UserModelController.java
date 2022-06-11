package com.gft.gdesk.controller;


import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.service.UserModelLoginService;
import com.gft.gdesk.service.UserModelService;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/user-by-id")
    public UserModel getUserById(@RequestBody Long id) {
        return userModelService.getUserById(id);
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel toLogin) {
        return userModelLoginService.login(toLogin);
    }

    @GetMapping("/wait-for-approval-users")
    public List<UserModel> getWaitForApprovalUsers() {
        return userModelService.getWaitForApprovalUsers();
    }

    @DeleteMapping("/delete-by-id")
    public void deleteUserById(@RequestBody Long id) {
        userModelService.deleteUserById(id);
    }
    @DeleteMapping("/delete-by-email")
    public void deleteUserByEmail(@RequestBody String email) {
        userModelService.deleteUserByEmail(email);
    }
}