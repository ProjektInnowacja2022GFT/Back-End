package com.gft.gdesk.service;


import com.gft.gdesk.dto.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserModelService {
    private final List<UserModel> users = new ArrayList<>();
    public static final String MESSAGE_SUCCESSFULLY_REGISTERED = "User successfully registered, now wait for approval";
    public static final String MESSAGE_WAITING_FOR_APPROVAL = "User is waiting for approval";
    public static final String MESSAGE_USER_EXISTS = "User with with this email already exists";
    private static final String WAIT_FOR_APPROVAL = "WAIT_FOR_APPROVAL";

    public List<UserModel> getAllUsers() {
        return users;
    }

    public UserModel getUsersById(int id) {
        if (id > users.size()) {
            return null;
        }
        return users.get(id);
    }

    public List<UserModel> getWaitForApprovalUsers() {
        List<UserModel> waitForApprovalUsers=new ArrayList<>();
        for (UserModel user : users) {
            if(WAIT_FOR_APPROVAL.equals(user.getStatus())) {
                waitForApprovalUsers.add(user);
            }
        }
        return waitForApprovalUsers;
    }

    public String registerUser(UserModel toRegister) {
        Optional<UserModel> userCheck = users.stream()
                .filter(x -> x.getEmail().equals(toRegister.getEmail())).findAny();
        if (userCheck.isPresent()) {
            UserModel userFromDb = userCheck.get();
            return WAIT_FOR_APPROVAL.equals(userFromDb.getStatus()) ? MESSAGE_WAITING_FOR_APPROVAL : MESSAGE_USER_EXISTS;
        }
        validateFields(toRegister);
        users.add(toRegister);
        return MESSAGE_SUCCESSFULLY_REGISTERED;
    }

    private void validateFields(UserModel toRegister) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]{2,}@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toRegister.getEmail());
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostConstruct
    public void setInitialUsers() {
        this.users.addAll(Arrays.asList(
                UserModel.builder().
                        id(0L).
                        name("Jan").
                        surname("Kowalski").
                        company("GFT").
                        email("jan.kowalski@gmail.com").
                        password("haslo123").
                        status("APPROVED").
                        build(),
                UserModel.builder().
                        id(1L).
                        name("Piotr").
                        surname("Jaworski").
                        company("Konkurencja").
                        email("jan.kowalski@gmail.com").
                        password("xd2137").
                        status("APPROVED").
                        build(),
                UserModel.builder().
                        id(2L).
                        name("Canadian").
                        surname("Enjoyer").
                        company("GFT").
                        email("canadian.enjoyer@gmail.com").
                        password("1337canada").
                        status("BLOCKED").
                        build()
        ));
    }
}
