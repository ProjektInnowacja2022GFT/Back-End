package com.gft.gdesk.service;

import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService
{
    private final UserRepository userRepository;
    private static final String APPROVED = "APPROVED";
    private static final String WAIT_FOR_APPROVAL = "WAIT_FOR_APPROVAL";

    public AdminService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void acceptNewUser(String status)
    {
        Optional<UserModel> user = Optional.ofNullable(userRepository.findByStatus(status));

        if(!user.isPresent())
        {
            throw new UserNotFoundException("User does not exist");
        }

        UserModel userToUpdate = user.get();
        if(WAIT_FOR_APPROVAL.equals(userToUpdate.getStatus()))
        {
            userToUpdate.setStatus(APPROVED);
        }
        userRepository.save(userToUpdate);
    }
}