package com.gft.gdesk.service;

import com.gft.gdesk.entity.Desk;
import com.gft.gdesk.entity.Reservation;
import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.exception.DeskAlreadyOccupiedException;
import com.gft.gdesk.exception.DeskNotFoundException;
import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.repository.DeskRepository;
import com.gft.gdesk.repository.ReservationRepository;
import com.gft.gdesk.repository.UserModelRepository;
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

    private final ReservationRepository reservationRepository;
    private final DeskRepository deskRepository;
    private final UserModelRepository userModelRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addReservation(Reservation toReservation) {
        try {
            loadUserByEmail(toReservation.getUser().getEmail());
            loadDeskById(toReservation.getDesk().getId());
            checkIfDeskIsFreeOnThisTime(toReservation.getDesk(), toReservation.getReservationsDateStart(), toReservation.getReservationsDateEnd());

            return reservationRepository.save(toReservation);

        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (DeskNotFoundException | DeskAlreadyOccupiedException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private UserModel loadUserByEmail(String email) throws UserNotFoundException {
        return userModelRepository.findUserModelByEmail(email).orElseThrow(() -> new UserNotFoundException(ExceptionMessages.INCORRECT_EMAIL));
    }

    private Desk loadDeskById(Long id) throws DeskNotFoundException {
        return deskRepository.findById(id).orElseThrow(() -> new DeskNotFoundException(ExceptionMessages.INCORRECT_DESK));
    }

    private void checkIfDeskIsFreeOnThisTime(Desk desk, LocalDate start, LocalDate end) {
        if (isDeskOccupied(desk, start, end)) {
            throw new DeskAlreadyOccupiedException(ExceptionMessages.DESK_ALREADY_OCCUPIED);
        }
    }

    private boolean isDeskOccupied(Desk desk, LocalDate start, LocalDate end) {
        return !reservationRepository.findAllByOccupyDeskStatus(desk.getId(), start, end).isEmpty();
    }
}
