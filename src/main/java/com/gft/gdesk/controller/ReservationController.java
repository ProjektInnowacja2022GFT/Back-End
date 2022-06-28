package com.gft.gdesk.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.gdesk.dto.AddReservation;
import com.gft.gdesk.entity.Reservation;
import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String,String>> newReservation(@RequestBody AddReservation toReservation) {
        return new ResponseEntity<>(Map.of("data", reservationsService.addReservation(toReservation)), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public List<Reservation> getReservationsForUser(@PathVariable Long id) {
        return reservationsService.getAllReservationsForUser(id);
    }

    @GetMapping("/{pickedDate}")
    public List<Reservation> getReservationsForDate( @PathVariable String pickedDate) {
        return reservationsService.getReservationsForDate(pickedDate);
    }

}
