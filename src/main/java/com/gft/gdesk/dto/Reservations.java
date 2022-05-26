package com.gft.gdesk.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Reservations {
    private long id;
    private Users user;
    private Desks desk;
    private LocalDate reservations_date_start;
    private LocalDate reservations_date_end;
}