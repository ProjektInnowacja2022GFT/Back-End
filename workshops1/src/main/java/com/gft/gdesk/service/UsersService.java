package com.gft.gdesk.service;


import com.gft.gdesk.dto.Users;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UsersService {
    private List<Users> users = new ArrayList<>();

    public List<Users> getAllUsers() {
        return users;
    }

    public Users getUsersById(int id){
        if(id > users.size()){
            return null;
        }
        return users.get(id);
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
