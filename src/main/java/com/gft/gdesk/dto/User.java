package com.gft.gdesk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String role;   //todo create role as enum
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String status; //todo create status as  enum
}
