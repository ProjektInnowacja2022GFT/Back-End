package com.gft.gdesk.service;


import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.repository.UserModelRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserModelService {
    private final PasswordEncoder passwordEncoder;
    private final List<UserModel> users = new ArrayList<>();
    private final UserModelRepository userModelRepository;
    private static final String WAIT_FOR_APPROVAL = "WAIT_FOR_APPROVAL";
    private final UserModelRepository userModelRepository;

    public List<UserModel> getAllUsers() {
        return userModelRepository.findAll();
    }

    public UserModel getUserById(Long id) {
        Optional<UserModel> user = userModelRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public List<UserModel> getWaitForApprovalUsers() {
        List<UserModel> waitForApprovalUsers = new ArrayList<>();
        for (UserModel user : users) {
            if (WAIT_FOR_APPROVAL.equals(user.getStatus())) {
                waitForApprovalUsers.add(user);
            }
        }
        return waitForApprovalUsers;
    }

    public String registerUser(UserModel toRegister) {
        Optional<UserModel> userCheck = users.stream().filter(x -> x.getEmail().equals(toRegister.getEmail())).findAny();
        if (userCheck.isPresent()) {
            UserModel userFromDb = userCheck.get();
            return WAIT_FOR_APPROVAL.equals(userFromDb.getStatus()) ? "User is waiting for approval" : "User with with this email already exists";
        }
        String encodedPassword = passwordEncoder.encode(toRegister.getPassword());
        toRegister.setPassword(encodedPassword);
        validateFields(toRegister);
        users.add(toRegister);
        return "User successfully registered, now wait for approval";
    }

    private void validateFields(UserModel toRegister) {
        Pattern pattern = Pattern.compile("^[\\w-.]{2,}@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toRegister.getEmail());
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteUserById(long id) {
        UserModel user = getUserById(id);
        userModelRepository.delete(user);
    }

    public void deleteUserByEmail(String email) {
        UserModel userByEmail = getUserByEmail(email);
        userModelRepository.delete(userByEmail);
    }
    private UserModel getUserByEmail(String email) {
        Optional<UserModel> user = userModelRepository.findUserModelByEmail(email);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }
}
