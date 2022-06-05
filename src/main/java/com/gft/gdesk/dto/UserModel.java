package com.gft.gdesk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

public class UserModel {
    private long id;
    private String name;
    private String surname;
    private String company;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String status = "WAIT_FOR_APPROVAL"; //todo create role as enum
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private static final String ROLE = "USER";   //todo create status as  enum
}