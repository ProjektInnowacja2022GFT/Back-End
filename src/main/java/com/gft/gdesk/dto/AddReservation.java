package com.gft.gdesk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddReservation {
    private long deskId;
    private long userId;
    private String reservationDateStart;
}
