package com.example.demo.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TeamDto {

    private String name;

    private String nameOfCoach;

    private String surnameOfCoach;

    private Integer budget;
}
