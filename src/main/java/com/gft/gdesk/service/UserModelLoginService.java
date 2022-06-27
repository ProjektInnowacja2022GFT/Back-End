package com.gft.gdesk.service;

import com.gft.gdesk.dto.AuthenticationRequest;
import com.gft.gdesk.dto.AuthenticationResponse;
import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.repository.UserModelRepository;
import com.gft.gdesk.security.MyUserDetails;
import com.gft.gdesk.util.ExceptionMessages;
import com.gft.gdesk.util.JwtUtil;
import com.gft.gdesk.util.UserModelStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;

@Service
@AllArgsConstructor
@Slf4j
public class UserModelLoginService {

    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    public String login(AuthenticationRequest toLogin) {
        MyUserDetails userDetails = null;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(toLogin.getEmail(), toLogin.getPassword())
            );
            userDetails = (MyUserDetails) authentication.getPrincipal();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticateUser(UserModel user) throws AuthenticationException {
        switch (user.getStatus()) {
            case UserModelStatus.WAITING_FOR_APPROVAL:
                throw new AuthenticationException(ExceptionMessages.ACCOUNT_PENDING_FOR_APPROVAL);
            case UserModelStatus.BLOCKED:
                throw new AuthenticationException(ExceptionMessages.ACCOUNT_BLOCKED);
        }
    }

    private UserModel loadUserByEmail(String email) throws UserNotFoundException {
        return userModelRepository.findUserModelByEmail(email).orElseThrow(() -> new UserNotFoundException(ExceptionMessages.INCORRECT_EMAIL));
    }

    private void validatePassword(UserModel user, String password) throws UserNotFoundException {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException(ExceptionMessages.INCORRECT_EMAIL_OR_PWD);
        }
    }
}
