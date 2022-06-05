package com.tests.service;

import com.tests.api.CarApi;
import com.tests.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    private CarApi carApi;
    private CarService carService;

    @BeforeEach
    void setup() {
        carApi = mock(CarApi.class);
        carService = new CarService(carApi);
    }

    @Test
    void shouldReturnTwoCars() {
        List<Car> cars = Arrays.asList(
                Car.builder()
                        .name("Audi a3")
                        .price(3000)
                        .year(2001)
                        .build(),
                Car.builder()
                        .name("Fiat 126p")
                        .price(1500)
                        .year(1987)
                        .build(),
                Car.builder()
                        .name("Audi b7")
                        .price(25000)
                        .year(2007)
                        .build());

        when(carApi.getCarsByUsername("userOne")).thenReturn(cars);

        List<Car> result = carService.getCarsByUsernameRelatedToAudi("userOne");

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnZeroCarsWhenThereIsNoCarsForUsername() {
        when(carApi.getCarsByUsername("nobody")).thenReturn(Collections.emptyList());

        List<Car> result = carService.getCarsByUsernameRelatedToAudi("nobody");

        assertEquals(0, result.size());
    }

    @Test
    void shouldCallMethodGetCarsByUsernameTwice() {
        when(carApi.getCarsByUsername(anyString()))
                .thenReturn(Collections.emptyList())
                .thenReturn(Collections.singletonList(
                        Car.builder()
                                .name("Audi a7")
                                .price(3001)
                                .year(2002)
                                .build()));

        carService.getCarsByUsernameRelatedToAudi(anyString());
        carService.getCarsByUsernameRelatedToAudi(anyString());

        verify(carApi, times(2)).getCarsByUsername(anyString());
    }

}
