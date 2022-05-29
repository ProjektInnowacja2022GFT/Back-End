package com.gft.gdesk.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<UserModel> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "desk")
    private List<Desk> desks;

    private LocalDate reservaretionsDateStart;
    private LocalDate reservationsDateEnd;
}