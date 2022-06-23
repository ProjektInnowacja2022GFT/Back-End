package com.gft.gdesk.cron;

import com.gft.gdesk.entity.Reservation;
import com.gft.gdesk.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpiredReservation {
    ReservationRepository reservationRepository;

    @Scheduled(cron = "0 0 * * *", zone = "Europe/Paris")
    public void cronJobSch() {
        LocalDate currentDate = LocalDate.now();
        reservationRepository.deleteAllExpiredReservation(currentDate);
    }
}
