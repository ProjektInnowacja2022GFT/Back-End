package com.gft.gdesk.controller;

import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.service.UserModelLoginService;
import com.gft.gdesk.service.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserModelController {

    private final UserModelService userModelService;
    private final UserModelLoginService userModelLoginService;

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

}