package com.tests.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Car {

    private String name;

    private int price;

    private int year;
}
