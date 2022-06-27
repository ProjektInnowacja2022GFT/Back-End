package com.gft.gdesk.controller;

import com.gft.gdesk.dto.AuthenticationRequest;
import com.gft.gdesk.dto.AuthenticationResponse;
import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.security.MyUserDetails;
import com.gft.gdesk.service.MyUserDetailsService;
import com.gft.gdesk.service.UserModelLoginService;
import com.gft.gdesk.service.UserModelService;
import com.gft.gdesk.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserModelController {

    private final UserModelService userModelService;
    private final UserModelLoginService userModelLoginService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel toRegister) {
        return userModelService.registerUser(toRegister);
    }

    @GetMapping("/all-users")
    public List<UserModel> getUsers() {
        return userModelService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        try {
            return userModelService.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest toLogin) throws Exception {
        return ResponseEntity.ok(userModelLoginService.login(toLogin));
    }

    @GetMapping("/wait-for-approval-users")
    public List<UserModel> getWaitForApprovalUsers() {
        return userModelService.getWaitForApprovalUsers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userModelService.deleteUserById(id);
    }

}