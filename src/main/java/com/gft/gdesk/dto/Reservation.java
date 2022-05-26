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
    private UserModel user;
    private Desk desk;
    private LocalDate reservaretionsDateStart;
    private LocalDate reservationsDateEnd;
}