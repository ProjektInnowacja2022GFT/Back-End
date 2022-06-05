package com.tests.service;

import com.tests.api.CarApi;
import com.tests.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTestWithAnnotations {

    @Mock
    private CarApi carApi;

    @InjectMocks
    private CarService carService;

    @Test
    void shouldReturnTwoCars() {
        when(carApi.getCarsByUsername("user1")).thenReturn(Arrays.asList(
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
                        .build()));

        List<Car> result = carService.getCarsByUsernameRelatedToAudi("user1");

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
                                .name("Audi a3")
                                .price(3000)
                                .year(2001)
                                .build()));

        carService.getCarsByUsernameRelatedToAudi(anyString());
        carService.getCarsByUsernameRelatedToAudi(anyString());

        verify(carApi, times(2)).getCarsByUsername(anyString());
    }

}
