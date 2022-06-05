package com.gft.gdesk.service;

import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.exception.loginExceptions.UserNotFoundException;
import com.gft.gdesk.repository.UserModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;

@Service
@AllArgsConstructor
public class UserModelLoginService {
    private static final String USER_NOT_EXISTS_MSG = "Incorrect email or password";
    private static final String USER_PENDING_MSG = "Your account is still pending approval";
    private static final String USER_BLOCKED_MSG = "Your account has been rejected";
    private static final String USER_STATUS_PENDING = "WAITING_FOR_APPROVAL";
    private static final String USER_STATUS_BLOCKED = "BLOCKED";
    private final UserModelRepository userModelRepository;


    public UserModel login(UserModel toLogin) {
        try {
            UserModel user = loadUserByEmail(toLogin.getEmail());
            validatePassword(user, toLogin.getPassword());
            authenticateUser(user);
            return user;
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    private void authenticateUser(UserModel user) throws AuthenticationException {
        switch (user.getStatus()) {
            case USER_STATUS_PENDING:
                throw new AuthenticationException(USER_PENDING_MSG);
            case USER_STATUS_BLOCKED:
                throw new AuthenticationException(USER_BLOCKED_MSG);
        }
    }

    private UserModel loadUserByEmail(String email) throws UserNotFoundException {
        return userModelRepository
                .findUserModelByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_EXISTS_MSG));
    }

    private void validatePassword(UserModel user, String password) throws UserNotFoundException {
        if (!password.equals(user.getPassword())) {
            throw new UserNotFoundException(USER_NOT_EXISTS_MSG);
        }
    }
}
