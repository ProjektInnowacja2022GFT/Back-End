package com.gft.gdesk.controller;


import com.gft.gdesk.dto.Users;
import com.gft.gdesk.service.UsersService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/api/v1/users")
@RestController

@Getter
@Setter

public class UsersController {

    private UsersService userService;

    public UsersController(UsersService userService){
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public Users login(@RequestBody Users toLogin){
        return userService.loginService.login(toLogin);
    }

}