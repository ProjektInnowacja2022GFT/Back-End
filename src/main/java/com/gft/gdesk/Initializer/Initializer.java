package com.gft.gdesk.Initializer;

import com.gft.gdesk.dto.Desk;
import com.gft.gdesk.dto.Reservation;
import com.gft.gdesk.dto.UserModel;
import com.gft.gdesk.repository.DeskRepository;
import com.gft.gdesk.repository.ReservationRepository;
import com.gft.gdesk.repository.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@SpringBootApplication
public class Initializer implements CommandLineRunner {

    private final DeskRepository deskRepository;
    private final ReservationRepository reservationRepository;
    private final UserModelRepository userModelRepository;

    private final List<Desk> desks = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();
    private final List<UserModel> users = new ArrayList<>();


    @Override
    public void run(String... args) throws Exception {
        addUserModels();
        addDesks();
        addReservations();

        userModelRepository.saveAll(users);
        deskRepository.saveAll(desks);
        reservationRepository.saveAll(reservations);
    }

    private void addReservations() {
        this.reservations.addAll(Arrays.asList(
                Reservation.builder().
                        id(0L).
                        user(users.get(0)).
                        desk(desks.get(0)).
                        reservationsDateStart(LocalDate.now()).
                        reservationsDateEnd(LocalDate.now().plusDays(3)).
                        build(),
                Reservation.builder().
                        id(1L).
                        user(users.get(1)).
                        desk(desks.get(1)).
                        reservationsDateStart(LocalDate.now()).
                        reservationsDateEnd(LocalDate.now().plusDays(4)).
                        build(),
                Reservation.builder().
                        id(2L).
                        user(users.get(2)).
                        desk(desks.get(2)).
                        reservationsDateStart(LocalDate.now().plusDays(1)).
                        reservationsDateEnd(LocalDate.now().plusDays(2)).
                        build()
        ));
    }

    private void addUserModels() {
        this.users.addAll(Arrays.asList(
                UserModel.builder().
                        id(0L).
                        name("Jan").
                        surname("Kowalski").
                        company("GFT").
                        email("jan.kowalski@gmail.com").
                        password("haslo123").
                        status("APPROVED").
                        build(),
                UserModel.builder().
                        id(1L).
                        name("Piotr").
                        surname("Jaworski").
                        company("Konkurencja").
                        email("jan.kowalski@gmail.com").
                        password("xd2137").
                        status("APPROVED").
                        build(),
                UserModel.builder().
                        id(2L).
                        name("Canadian").
                        surname("Enjoyer").
                        company("GFT").
                        email("canadian.enjoyer@gmail.com").
                        password("1337canada").
                        status("BLOCKED").
                        build()
        ));
    }

    private void addDesks() {
        this.desks.addAll(Arrays.asList(
                Desk.builder().
                        id(0L).
                        sector("A").
                        deskNumber(11).
                        floor(1).
                        build(),
                Desk.builder().
                        id(1L).
                        sector("A").
                        deskNumber(21).
                        floor(1).
                        build(),
                Desk.builder().
                        id(2L).
                        sector("C").
                        deskNumber(37).
                        floor(2).
                        build()
        ));
    }
}
