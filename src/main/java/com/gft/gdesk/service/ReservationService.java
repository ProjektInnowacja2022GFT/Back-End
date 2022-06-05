package com.gft.gdesk.service;

import com.gft.gdesk.dto.Desk;
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
}
