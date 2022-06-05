package com.tests.api;

import com.tests.model.Car;

import java.util.List;

public interface CarApi {

    List<Car> getCarsByUsername(String username);

}
