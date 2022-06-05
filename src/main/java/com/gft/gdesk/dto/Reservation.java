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
    private UserModel user;
    private LocalDate reservaretionsDateStart;
    private LocalDate reservationsDateEnd;
}