package com.gft.gdesk.controller;

import com.gft.gdesk.dto.Reservation;
import com.gft.gdesk.service.ReservationService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/reservation")
@RestController

@Getter
@Setter
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/getReservations")
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }
}
