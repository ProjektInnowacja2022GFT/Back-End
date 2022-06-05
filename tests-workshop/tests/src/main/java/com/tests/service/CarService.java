package com.tests.service;

import com.tests.api.CarApi;
import com.tests.model.Car;

import java.util.List;
import java.util.stream.Collectors;


// CarService SUT System under test
// CarApi dependency, implementation not available
public class CarService {

    public static String getAUDI() {
        return AUDI;
    }

    public CarApi getCarApi() {
        return carApi;
    }

    private static final String AUDI = "Audi";
    private final CarApi carApi;

    public CarService(CarApi carApi) {
        this.carApi = carApi;
    }

    public List<Car> getCarsByUsernameRelatedToAudi(String username) {
        return carApi.getCarsByUsername(username).stream()
                .filter(car -> car.getName().contains(AUDI))
                .collect(Collectors.toList());
    }

}
