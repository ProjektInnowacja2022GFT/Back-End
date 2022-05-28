package com.gft.gdesk.repository;


import com.gft.gdesk.dto.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>
{
    UserModel findByStatus(String status);
}
