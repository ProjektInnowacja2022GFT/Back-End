package com.gft.gdesk.service;

import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.repository.UserModelRepository;
import com.gft.gdesk.util.ExceptionMessages;
import com.gft.gdesk.util.UserModelStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;

@Service
@AllArgsConstructor
public class UserModelLoginService {

    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;

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
