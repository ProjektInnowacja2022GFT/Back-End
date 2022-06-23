package com.gft.gdesk.service;

import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.exception.UserStatusAlreadyChangedException;

import com.gft.gdesk.repository.UserModelRepository;
import com.gft.gdesk.util.UserModelStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserModelRepository userModelRepository;

    public void acceptNewUser(Long id) throws UserNotFoundException, UserStatusAlreadyChangedException {
        changeUserModelStatus(id, UserModelStatus.APPROVED);
    }

    public void blockNewUser(Long id) throws UserNotFoundException, UserStatusAlreadyChangedException {
        changeUserModelStatus(id, UserModelStatus.BLOCKED);
    }

    private void changeUserModelStatus(Long id, String status) throws UserNotFoundException, UserStatusAlreadyChangedException {
        UserModel user = userModelRepository.findUserModelById(id).orElseThrow(() -> new UserNotFoundException("UserModel does not exist id: " + id));
        if ((UserModelStatus.WAITING_FOR_APPROVAL.equals(user.getStatus()))) {
            user.setStatus(status);
            userModelRepository.save(user);
        } else {
            throw new UserStatusAlreadyChangedException(user.getStatus());
        }
    }
}
