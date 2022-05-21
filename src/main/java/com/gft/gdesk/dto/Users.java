package com.gft.gdesk.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Users {
    private long id;
    private String name;
    private String surname;
    private String company;
    private String email;
    private String password;
    private String role;
    private String status;
}
