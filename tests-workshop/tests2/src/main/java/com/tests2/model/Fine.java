package com.tests2.model;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Fine {

    private String driver;

    private int amountOfMoney;

    private String licenceStatus;

}
