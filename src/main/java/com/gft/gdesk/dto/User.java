package com.gft.gdesk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {
    private long id;
    private String name;
    private String surname;
    private String company;
    private String email;
    private String password;
    private String role;
    private String status;
}
