package com.gft.gdesk.service;

import com.gft.gdesk.dto.User;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService
{
    private final UserRepository userRepository;
    private static final String WAITING_FOR_APPROVAL = "WAITING_FOR_APPROVAL";
    private static final String APPROVED = "APPROVED";


    public AdminService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void acceptNewUser(Long Id)
    {
        Optional<User> user = Optional.ofNullable(userRepository.findAllById(Id));

        if(!user.isPresent())
        {
            throw new UserNotFoundException("User does not exist");
        }

        User userToUpdate = user.get();
        if(userToUpdate.getStatus().equals(WAITING_FOR_APPROVAL))
        {
            userToUpdate.setStatus(APPROVED);
        }
        userRepository.save(userToUpdate);
    }
}