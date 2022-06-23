package com.gft.gdesk.repository;

import com.gft.gdesk.entity.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {
    
    @Query("SELECT d FROM Desk d LEFT JOIN Reservation r ON d.id=r.desk.id WHERE r.desk.id IS NULL")
    List<Desk> findAllFreeDesks();

}
