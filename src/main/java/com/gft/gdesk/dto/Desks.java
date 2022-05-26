package com.gft.gdesk.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Desks {
    private long id;
    private String sector;
    private int desk_number;
    private int floor;
}
