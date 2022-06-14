package com.gft.gdesk.controller;

import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.service.UserModelLoginService;
import com.gft.gdesk.service.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/user-by-id/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        try {
            return userModelService.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel toLogin) {
        return userModelLoginService.login(toLogin);
    }

    @GetMapping("/wait-for-approval-users")
    public List<UserModel> getWaitForApprovalUsers() {
        return userModelService.getWaitForApprovalUsers();
    }

    @DeleteMapping("/user-by-id/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userModelService.deleteUserById(id);
    }

}