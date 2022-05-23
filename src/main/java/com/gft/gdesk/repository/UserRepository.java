package com.gft.gdesk.repository;

import com.gft.gdesk.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>
{
    Users findByStatus(String status);
    Users findAllById(Long Id);
}
