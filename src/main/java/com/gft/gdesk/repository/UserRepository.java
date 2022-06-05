package com.gft.gdesk.repository;

import com.gft.gdesk.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByStatus(String status);
    User findAllById(Long Id);
}
