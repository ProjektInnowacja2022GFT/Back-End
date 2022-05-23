package com.gft.gdesk.service;

import com.gft.gdesk.dto.Reservations;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ReservationsService {
    private List<Reservations> reservations = new ArrayList<>();
    private UsersService usersService;
    private DesksService desksService;

    public ReservationsService(UsersService usersService,
                               DesksService desksService) {
        this.usersService = usersService;
        this.desksService = desksService;
    }

    public List<Reservations> getAllReservations() {
        return reservations;
    }

    @PostConstruct
    public void setInitialReservations() {
        this.reservations.addAll(Arrays.asList(
                Reservations.builder().
                        id(0L).
                        user(usersService.getUsersById(0)).
                        desk(desksService.getDesksById(0)).
                        reservations_date_start(LocalDate.now()).
                        reservations_date_end(LocalDate.now().plusDays(3)).
                        build(),
                Reservations.builder().
                        id(1L).
                        user(usersService.getUsersById(1)).
                        desk(desksService.getDesksById(1)).
                        reservations_date_start(LocalDate.now()).
                        reservations_date_end(LocalDate.now().plusDays(4)).
                        build(),
                Reservations.builder().
                        id(2L).
                        user(usersService.getUsersById(2)).
                        desk(desksService.getDesksById(2)).
                        reservations_date_start(LocalDate.now().plusDays(1)).
                        reservations_date_end(LocalDate.now().plusDays(2)).
                        build()
        ));
    }
}
