package com.gft.gdesk.service;

import com.gft.gdesk.dto.Users;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.exception.UserStatusAlreadyChangedException;
import com.gft.gdesk.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final static String WAIT_STATUS = "WAIT_FOR_APPROVAL";
    private final static String BLOCKED_STATUS = "BLOCKED";

    public void blockNewUser(long id) throws UserNotFoundException, UserStatusAlreadyChangedException {
        Optional<Users> user = userRepository.findById(id);
        Users userObject;
        if(user.isEmpty()){
            throw new UserNotFoundException(id);
        }else{
            userObject = user.get();
        }
        if(WAIT_STATUS.equals(userObject.getStatus())){
            userObject.setStatus(BLOCKED_STATUS);
        }else{
            throw new UserStatusAlreadyChangedException(userObject.getStatus());
        }
    }


}
