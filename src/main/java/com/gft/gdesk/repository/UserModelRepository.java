package com.gft.gdesk.repository;

import com.gft.gdesk.dto.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Long> {
    UserModel findByStatus(String status);

    Optional<UserModel> findUserModelByEmail(String email);
    
    UserModel findAllById(Long Id);

}
