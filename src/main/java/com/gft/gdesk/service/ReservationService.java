package com.gft.gdesk.service;

import com.gft.gdesk.dto.Reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private final List<Reservation> reservations = new ArrayList<>();
    private final UserModelService userModelService;
    private final DeskService desksService;

    public ReservationService(UserModelService userModelService,
                              DeskService desksService) {
        this.userModelService = userModelService;

        this.desksService = desksService;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}
