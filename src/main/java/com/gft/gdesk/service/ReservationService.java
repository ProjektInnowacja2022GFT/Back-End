package com.gft.gdesk.service;

import com.gft.gdesk.dto.Desk;
import com.gft.gdesk.dto.Reservation;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ReservationService {
    private List<Reservation> reservations = new ArrayList<>();
    private UserModelService usersService;
    private DeskService desksService;

    public ReservationService(UserModelService usersService,
                              DeskService desksService) {
        this.usersService = usersService;
        this.desksService = desksService;
    }

    public List<Desk> getFreeDesks() {
        List<Desk> freeDesks=new ArrayList<>();
        for (Desk desk : desksService.getAllDesks())
        {
            if (isDeskFree(desk))
            {
                freeDesks.add(desk);
            }
        }
        return freeDesks;
    }

    private boolean isDeskFree(Desk desk) {
        Reservation result=reservations.stream()
                .filter(reservation -> desk.getId()==reservation.getDesk().getId())
                .findAny()
                .orElse(null);

        return result == null;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    @PostConstruct
    public void setInitialReservations() {
        this.reservations.addAll(Arrays.asList(
                Reservation.builder().
                        id(0L).
                        user(usersService.getUsersById(0)).
                        desk(desksService.getDesksById(0)).
                        reservaretionsDateStart(LocalDate.now()).
                        reservationsDateEnd(LocalDate.now().plusDays(3)).
                        build(),
                Reservation.builder().
                        id(1L).
                        user(usersService.getUsersById(1)).
                        desk(desksService.getDesksById(1)).
                        reservaretionsDateStart(LocalDate.now()).
                        reservationsDateEnd(LocalDate.now().plusDays(4)).
                        build(),
                Reservation.builder().
                        id(2L).
                        user(usersService.getUsersById(2)).
                        desk(desksService.getDesksById(2)).
                        reservaretionsDateStart(LocalDate.now().plusDays(1)).
                        reservationsDateEnd(LocalDate.now().plusDays(2)).
                        build()
        ));
    }
}
