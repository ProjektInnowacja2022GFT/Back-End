package com.gft.gdesk.controller;

import com.gft.gdesk.dto.Users;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.exception.UserStatusAlreadyChangedException;
import com.gft.gdesk.service.AdminService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/api/v1/adminPanel")
@RestController

@Getter
@Setter

public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PutMapping("/blockUser/{id}")
    public void blockUserById(@PathVariable int id){
        try{
            adminService.blockNewUser(id);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (UserStatusAlreadyChangedException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        throw new ResponseStatusException(HttpStatus.OK);
    }
}
