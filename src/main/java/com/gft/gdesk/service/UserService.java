package com.gft.gdesk.service;


import com.gft.gdesk.dto.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private List<User> users;
    public List<User> getAllUsers() {
        return users;
    }

    public User getUsersById(int id){
        if(id > users.size()){
            return null;
        }
        return users.get(id);
    }

    @PostConstruct
    public void setInitialUsers() {
        this.users.addAll(Arrays.asList(
                User.builder().
                        id(0L).
                        name("Jan").
                        surname("Kowalski").
                        company("GFT").
                        email("jan.kowalski@gmail.com").
                        password("haslo123").
                        role("USER").
                        status("APPROVED").
                        build(),
                User.builder().
                        id(1L).
                        name("Piotr").
                        surname("Jaworski").
                        company("Konkurencja").
                        email("jan.kowalski@gmail.com").
                        password("xd2137").
                        role("USER").
                        status("APPROVED").
                        build(),
                User.builder().
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
