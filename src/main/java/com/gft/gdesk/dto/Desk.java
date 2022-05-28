package com.gft.gdesk.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Desk {
    private long id;
    private String sector;
    private int deskNumber;
    private int floor;
}
