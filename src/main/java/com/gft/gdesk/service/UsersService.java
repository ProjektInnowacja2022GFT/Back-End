package com.gft.gdesk.service;


import com.gft.gdesk.dto.Book;
import com.gft.gdesk.dto.Users;
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
public class UsersService {
    private List<Users> users = new ArrayList<>();

    public List<Users> getAllUsers() {
        return users;
    }

    public Users getUsersById(int id) {
        if (id > users.size()) {
            return null;
        }
        return users.get(id);
    }

    public String registerUser(Users toRegister) {
        Optional<Users> userCheck = users.stream()
                .filter(x -> x.getEmail().equals(toRegister.getEmail())).findAny();
        if (userCheck.isPresent()) {
            Users userFromDb = userCheck.get();
            return "WAIT_FOR_APPROVAL".equals(userFromDb.getStatus()) ? "User is waiting for approval" : "User with with this email already exists";
        }
        validateFields(toRegister);
        users.add(toRegister);
        return "User successfully registered, now wait for approval";
    }

    private void validateFields(Users toRegister) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]{2,}@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toRegister.getEmail());
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostConstruct
    public void setInitialUsers() {
        this.users.addAll(Arrays.asList(
                Users.builder().
                        id(0L).
                        name("Jan").
                        surname("Kowalski").
                        company("GFT").
                        email("jan.kowalski@gmail.com").
                        password("haslo123").
                        role("USER").
                        status("APPROVED").
                        build(),
                Users.builder().
                        id(1L).
                        name("Piotr").
                        surname("Jaworski").
                        company("Konkurencja").
                        email("jan.kowalski@gmail.com").
                        password("xd2137").
                        role("USER").
                        status("APPROVED").
                        build(),
                Users.builder().
                        id(2L).
                        name("Canadian").
                        surname("Enjoyer").
                        company("GFT").
                        email("canadian.enjoyer@gmail.com").
                        password("1337canada").
                        role("USER").
                        status("BLOCKED").
                        build()
        ));
    }
}
