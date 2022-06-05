package com.gft.gdesk.service;

import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.exception.loginExceptions.UserNotFoundException;
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
    private static final String USER_STATUS_PENDING = "WAIT_FOR_APPROVAL";
    private static final String USER_STATUS_BLOCKED = "BLOCKED";

    //todo uncomment after integration with database
    //private final UsersRepository userRepository;


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
        if (email.equals("testMail@a.pl"))
            return UserModel.builder()
                    .id(0L)
                    .name("Jan")
                    .surname("Kowalski")
                    .company("GFT")
                    .email("jan.kowalski@gmail.com")
                    .password("haslo123")
                    .status("APPROVED")
                    .build();
        else if (email.equals("abc@a.pl")) {
            return UserModel.builder()
                    .id(0L)
                    .name("Mirek")
                    .surname("Karas")
                    .company("ESP")
                    .email("abc@a.pl")
                    .password("abcd")
                    .status("BLOCKED")
                    .build();
        } else
            throw new UserNotFoundException(USER_NOT_EXISTS_MSG);

        //todo uncomment && remove static content after integration with database
//        return userRepository
//                .findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException(USER_DOSENT_EXISTS_MSG));
    }

    private void validatePassword(UserModel user, String password) throws UserNotFoundException {
        if (!password.equals(user.getPassword())) {
            throw new UserNotFoundException(USER_NOT_EXISTS_MSG);
        }
    }
}
