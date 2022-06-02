package com.gft.gdesk.service;

import com.gft.gdesk.dto.User;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.exception.UserStatusAlreadyChangedException;
import com.gft.gdesk.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final static String WAIT_FOR_APPROVAL_STATUS = "WAIT_FOR_APPROVAL";
    private final static String BLOCKED_STATUS = "BLOCKED";

    public void blockNewUser(long id) throws UserNotFoundException, UserStatusAlreadyChangedException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if(!(WAIT_FOR_APPROVAL_STATUS.equals(user.getStatus()))){
            throw new UserStatusAlreadyChangedException(user.getStatus());
        }
        user.setStatus(BLOCKED_STATUS);
    }


}
