package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.domain.CarType;
import com.nhnacadey.gw1.parking.domain.User;
import com.nhnacadey.gw1.parking.exception.NotAllowedCarTypeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntranceTest {

    Entrance entrance;
    Car car;

    @BeforeEach
    void setUp() {
        car = mock(Car.class);
        entrance = new Entrance(car);
    }

    @Test
    void entrance_carType_isTooLarge_thenNotAllowedCarTypeException() {

        when(car.getCarType()).thenReturn(CarType.LARGE);

        assertThatThrownBy(() -> entrance.scan())
                .isInstanceOf(NotAllowedCarTypeException.class)
                .hasMessageContaining("Not Allowed CarType in ParkingSpace");

    }

    @Test
    void entrance_carType_medium_successEnter() {

        when(car.getCarType()).thenReturn(CarType.MEDIUM);

        assertDoesNotThrow(() -> entrance.scan());
    }

    @Test
    void entrance_carType_small_successEnter() {

        when(car.getCarType()).thenReturn(CarType.SMALL);

        assertDoesNotThrow(() -> entrance.scan());
    }


}