package com.gft.gdesk.repository;

import com.gft.gdesk.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long id);



}
