package com.gft.gdesk.service;


import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.repository.UserModelRepository;
import com.gft.gdesk.util.ExceptionMessages;
import com.gft.gdesk.util.UserModelStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserModelService {
    private static final String SUCCESSFULLY_REGISTERED = "User successfully registered, now wait for approval";

    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers() {
        return userModelRepository.findAll();
    }

    public UserModel getUserById(Long id) throws UserNotFoundException {
        return userModelRepository.findUserModelById(id).orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));
    }

    public List<UserModel> getWaitForApprovalUsers() {
        return userModelRepository.findAllByStatus(UserModelStatus.WAITING_FOR_APPROVAL);
    }

    public String registerUser(UserModel toRegister) {
        Optional<UserModel> userCheck = userModelRepository.findUserModelByEmail(toRegister.getEmail());

        if (userCheck.isPresent()) {
            UserModel userFromDb = userCheck.get();
            return UserModelStatus.WAITING_FOR_APPROVAL.equals(userFromDb.getStatus()) ? ExceptionMessages.ACCOUNT_PENDING_FOR_APPROVAL : ExceptionMessages.USER_ALREADY_EXISTS;
        }
        String encodedPassword = passwordEncoder.encode(toRegister.getPassword());
        toRegister.setPassword(encodedPassword);
        validateFields(toRegister);
        userModelRepository.save(toRegister);
        return SUCCESSFULLY_REGISTERED;
    }

    private void validateFields(UserModel toRegister) {
        Pattern pattern = Pattern.compile("^[\\w-.]{2,}@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toRegister.getEmail());
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteUserById(long id) {
        userModelRepository.deleteById(id);
    }
}
