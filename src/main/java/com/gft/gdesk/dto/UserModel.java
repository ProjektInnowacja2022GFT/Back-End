package com.gft.gdesk.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserModel {
    private long id;
    private String name;
    private String surname;
    private String company;
    private String email;
    private String password;
    private String status = "WAIT_FOR_APPROVAL";
    private static final String ROLE = "USER";  // move to enum
}