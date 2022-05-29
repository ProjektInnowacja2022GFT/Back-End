package com.gft.gdesk.controller;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.exception.UserStatusAlreadyChangedException;
import com.gft.gdesk.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/v1/admin-panel")
@RestController

public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PutMapping("/block-user/{id}")
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
