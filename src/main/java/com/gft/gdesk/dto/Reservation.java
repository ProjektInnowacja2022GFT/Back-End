package com.gft.gdesk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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