package com.gft.gdesk.repository;

import com.gft.gdesk.entity.Reservation;
import com.gft.gdesk.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(
            "SELECT r " +
                    "FROM Reservation r " +
                    "WHERE " +
                    "r.desk.id=:deskId AND " +
                    "((reservationsDateStart BETWEEN :start AND :end) OR " +
                    "(reservationsDateEnd BETWEEN :start AND :end) OR " +
                    "((:start BETWEEN reservationsDateStart AND reservationsDateEnd) AND " +
                    "(:end BETWEEN reservationsDateStart AND reservationsDateEnd)))"
    )
    List<Reservation> findAllByOccupyDeskStatus(long deskId, LocalDate start, LocalDate end);

    @Query("DELETE FROM Reservation WHERE reservationsDateEnd < :current")
    List<Reservation> deleteAllExpiredReservation(LocalDate current);

    List<Reservation> findAllByUserId(Long user);

    List<Reservation> findAllByReservationsDateStart(LocalDate date);
}
