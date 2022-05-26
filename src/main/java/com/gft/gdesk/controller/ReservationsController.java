package com.gft.gdesk.controller;

import com.gft.gdesk.dto.Reservations;
import com.gft.gdesk.service.DesksService;
import com.gft.gdesk.service.ReservationsService;
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
public class ReservationsController {

    private ReservationsService reservationsService;

    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping("/getReservations")
    public List<Reservations> getReservations() {
        return reservationsService.getAllReservations();
    }
}
