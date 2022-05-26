package com.gft.gdesk.controller;


import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.service.UserModelService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/api/v1/user")
@RestController

public class UserModelController {

    private UserModelService userService;

    public UserModelController(UserModelService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel toRegister) {
        return userService.registerUser(toRegister);
    }

    @GetMapping("/all-users")
    public List<UserModel> getUsers() {
        return userService.getAllUsers();
    }
}