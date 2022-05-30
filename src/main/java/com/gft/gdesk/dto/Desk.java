package com.gft.gdesk.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sector;
    private int deskNumber;
    private int floor;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "desk", cascade = CascadeType.ALL)
    private List<Reservation> reservationList;
}
