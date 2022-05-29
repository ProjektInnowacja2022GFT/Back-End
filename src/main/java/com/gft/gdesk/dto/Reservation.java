package com.gft.gdesk.dto;

import lombok.*;
import org.apache.catalina.User;

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

    @ManyToOne()
    private UserModel user;

    @ManyToOne()
    private Desk desk;

    private LocalDate reservaretionsDateStart;
    private LocalDate reservationsDateEnd;
}