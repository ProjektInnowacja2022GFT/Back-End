package com.gft.gdesk.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String company;
    private String email;
    private String password;
    private String status = "WAIT_FOR_APPROVAL";
    private static final String ROLE = "USER";  // move to enum
    @ManyToOne()
    private Reservation reservation;

}