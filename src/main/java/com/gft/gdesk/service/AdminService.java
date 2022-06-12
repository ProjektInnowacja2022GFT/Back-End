package com.gft.gdesk.service;

import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.exception.UserStatusAlreadyChangedException;

import com.gft.gdesk.repository.UserModelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final UserModelRepository userModelRepository;
    private static final String WAITING_FOR_APPROVAL = "WAITING_FOR_APPROVAL";
    private static final String APPROVED = "APPROVED";
    private final static String BLOCKED = "BLOCKED";


    public AdminService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    public void acceptNewUser(Long Id) {
        Optional<UserModel> user = userModelRepository.findById(Id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("UserModel does not exist");
        }

        UserModel userToUpdate = user.get();
        if (userToUpdate.getStatus().equals(WAITING_FOR_APPROVAL)) {
            userToUpdate.setStatus(APPROVED);
        }
        userModelRepository.save(userToUpdate);
    }

    public void blockNewUser(long id) throws UserNotFoundException, UserStatusAlreadyChangedException {
        UserModel user = userModelRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("UserModel does not exist id: " + id));
        if (!(WAITING_FOR_APPROVAL.equals(user.getStatus()))) {
            throw new UserStatusAlreadyChangedException(user.getStatus());
        }
        user.setStatus(BLOCKED);
        userModelRepository.save(user);
    }
}
