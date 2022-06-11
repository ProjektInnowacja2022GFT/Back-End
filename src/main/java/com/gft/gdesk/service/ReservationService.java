package com.gft.gdesk.service;

import com.gft.gdesk.dto.Desk;
import com.gft.gdesk.dto.Reservation;
import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.exception.DeskAlreadyOccupiedException;
import com.gft.gdesk.exception.DeskNotFoundException;
import com.gft.gdesk.exception.loginExceptions.UserNotFoundException;
import com.gft.gdesk.repository.DeskRepository;
import com.gft.gdesk.repository.ReservationRepository;
import com.gft.gdesk.repository.UserModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class ReservationService {
    private static final String USER_NOT_EXISTS_MSG = "Incorrect email";
    private static final String DESK_NOT_EXISTS_MSG = "Incorrect desk number";
    private static final String DESK_ALREADY_OCCUPIED_MSG = "Desk is already occupied";
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
            checkIfDeskIsFreeOnThisTime(toReservation.getDesk(),toReservation.getReservationsDateStart(), toReservation.getReservationsDateEnd());

            return reservationRepository.save(toReservation);

        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (DeskNotFoundException | DeskAlreadyOccupiedException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    private UserModel loadUserByEmail(String email) throws UserNotFoundException {
        return userModelRepository
                .findUserModelByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_EXISTS_MSG));
    }

    private Desk loadDeskById(Long id) throws DeskNotFoundException {
        return deskRepository
                .findById(id)
                .orElseThrow(() -> new DeskNotFoundException(DESK_NOT_EXISTS_MSG));
    }

    private void checkIfDeskIsFreeOnThisTime(Desk desk, LocalDate start, LocalDate end) {
        if(isDeskOccupied(desk, start, end))
        {
            throw new DeskAlreadyOccupiedException(DESK_ALREADY_OCCUPIED_MSG);
        }
    }
    private boolean isDeskOccupied(Desk desk, LocalDate start, LocalDate end) {
        return !reservationRepository.
                findAllByOccupyDeskStatus(desk.getId(), start, end).
                isEmpty();
    }
}
