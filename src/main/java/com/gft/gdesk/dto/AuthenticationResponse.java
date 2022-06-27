package com.gft.gdesk.dto;

import com.gft.gdesk.entity.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    String jwt;
    UserModel user;
}
