package com.gft.gdesk.service;

import com.gft.gdesk.dto.Users;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.exception.UserStatusAlreadyChangedException;
import com.gft.gdesk.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;


    public void blockNewUser(long id) throws UserNotFoundException, UserStatusAlreadyChangedException {
        Optional<Users> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException(id);
        }
        if(user.get().getStatus().equals("WAIT_FOR_APPROVAL")){
            user.get().setStatus("BLOCKED");
        }else{
            throw new UserStatusAlreadyChangedException(user.get().getStatus());
        }
    }


}
