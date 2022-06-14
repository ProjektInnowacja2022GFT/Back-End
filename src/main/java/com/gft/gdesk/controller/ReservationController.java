package com.gft.gdesk.controller;

import com.gft.gdesk.dto.Reservation;
import com.gft.gdesk.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationsService;

    @GetMapping("/all-reservations")
    public List<Reservation> getReservations() {
        return reservationsService.getAllReservations();
    }

    @PostMapping("/new-reservation")
    public Reservation newReservation(@RequestBody Reservation toReservation) {
        return reservationsService.addReservation(toReservation);
    }

}
