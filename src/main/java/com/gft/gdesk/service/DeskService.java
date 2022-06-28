package com.gft.gdesk.service;

import com.gft.gdesk.entity.Desk;
import com.gft.gdesk.entity.Reservation;
import com.gft.gdesk.exception.DeskNotFoundException;
import com.gft.gdesk.repository.DeskRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.gft.gdesk.repository.ReservationRepository;
import com.gft.gdesk.util.ExceptionMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeskService {

    private final DeskRepository deskRepository;
    private final ReservationRepository reservationRepository;

    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }

    public List<Desk> getFreeDesks() {
        return deskRepository.findAllFreeDesks();
    }

    public Desk getDeskById(Long id) throws DeskNotFoundException {
        return deskRepository.findById(id).orElseThrow(() -> new DeskNotFoundException(ExceptionMessages.INCORRECT_DESK));
    }
    public List<Desk> getFreeDesksOnDate(int floor, String pickedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(pickedDate, formatter);
        List<Desk> reservedDesksOnDate = reservationRepository.findAllByReservationsDateStart(date).stream()
                .map(Reservation::getDesk)
                .filter(desk -> desk.getFloor() == floor)
                .collect(Collectors.toList());
        List<Desk> desksFreeOnFloorForDate = deskRepository.findAllByFloor(floor);
        return desksFreeOnFloorForDate.stream().filter(desk -> !reservedDesksOnDate.contains(desk)).collect(Collectors.toList());
    }
}
