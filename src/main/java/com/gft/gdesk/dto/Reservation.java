package com.gft.gdesk.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Reservation {
    private long id;
    private User user;
    private Desk desk;
    private LocalDate reservations_date_start;
    private LocalDate reservations_date_end;
}