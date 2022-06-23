package com.gft.gdesk.service;

import com.gft.gdesk.entity.Desk;
import com.gft.gdesk.entity.Reservation;
import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.exception.DeskAlreadyOccupiedException;
import com.gft.gdesk.exception.DeskNotFoundException;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.repository.ReservationRepository;
import com.gft.gdesk.util.ExceptionMessages;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class ReservationService {

    private static final String SUCCESSFULLY_ADDED = "Reservation successfully added";

    private final ReservationRepository reservationRepository;
    private final UserModelService userModelService;
    private final DeskService deskService;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public String addReservation(Reservation toReservation) {
        try {
            UserModel userModel = userModelService.getUserById(toReservation.getUser().getId());
            Desk desk = deskService.getDeskById(toReservation.getDesk().getId());

            toReservation.setUser(userModel);
            toReservation.setDesk(desk);
            checkIfReservationHasCorrectDesk(toReservation.getDesk(), toReservation.getReservationsDateStart(), toReservation.getReservationsDateEnd());
            reservationRepository.save(toReservation);
            return SUCCESSFULLY_ADDED;

        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (DeskNotFoundException | DeskAlreadyOccupiedException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void checkIfReservationHasCorrectDesk(Desk desk, LocalDate start, LocalDate end) {
        if (isDeskInExistingReservation(desk, start, end)) {
            throw new DeskAlreadyOccupiedException(ExceptionMessages.DESK_ALREADY_OCCUPIED);
        }
    }

    private boolean isDeskInExistingReservation(Desk desk, LocalDate start, LocalDate end) {
        return !reservationRepository.findAllByOccupyDeskStatus(desk.getId(), start, end).isEmpty();
    }
}
