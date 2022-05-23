package com.gft.gdesk.service;

import com.gft.gdesk.dto.Users;
import com.gft.gdesk.exception.loginExceptions.UserNotFoundException;
import com.gft.gdesk.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;

@Service
@AllArgsConstructor
public class LoginService {
    private final UsersRepository userRepository;

    private final static String USER_DOSENT_EXISTS_MSG = "Incorrect email or password";
    private final static String USER_PENDING_MSG = "Your account is still pending approval";
    private final static String USER_REJECTED_MSG = "Your account has been rejected";

    public Users login(Users toLogin) {
        try {

            Users user = loadUserByEmail(toLogin.getEmail());
            validatePassword(user, toLogin.getPassword());
            authenticateUser(user);
            return getReducedUserInfo(user);

        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    private Users getReducedUserInfo(Users user) {
        return Users.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .company(user.getCompany())
                .email(user.getEmail())
                .role("USER")
                .build();
    }

    private void authenticateUser(Users user) throws AuthenticationException {
        switch (user.getStatus()) {
            case "WAIT_FOR_APPROVAL":
                throw new AuthenticationException(USER_PENDING_MSG);
            case "BLOCKED":
                throw new AuthenticationException(USER_REJECTED_MSG);
        }
    }

    private Users loadUserByEmail(String email) throws UserNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_DOSENT_EXISTS_MSG));
    }

    private void validatePassword(Users user, String password) throws UserNotFoundException {
        if (!password.equals(user.getPassword())) {
            throw new UserNotFoundException(USER_DOSENT_EXISTS_MSG);
        }
    }
}
