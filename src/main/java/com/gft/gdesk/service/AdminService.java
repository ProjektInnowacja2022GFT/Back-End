package com.gft.gdesk.service;

import com.gft.gdesk.dto.Users;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService
{
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void acceptNewUser(String status)
    {
        Optional<Users> user = Optional.ofNullable(userRepository.findByStatus(status));

        if(!user.isPresent())
        {
            throw new UserNotFoundException("User does not exist");
        }

        Users userToUpdate = user.get();
        if(userToUpdate.getStatus().equals("WAIT_FOR_APPROVAL"))
        {
            userToUpdate.setStatus("APPROVED");
        }
        userRepository.save(userToUpdate);
    }
}