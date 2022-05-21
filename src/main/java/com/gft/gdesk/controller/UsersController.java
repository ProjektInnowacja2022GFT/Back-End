package com.gft.gdesk.controller;


import com.gft.gdesk.dto.Users;
import com.gft.gdesk.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @PostMapping("/register")
    public String registerUser(@RequestBody Users toRegister) {
        return userService.registerUser(toRegister);
    }

    @GetMapping("/getUsers")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }
}