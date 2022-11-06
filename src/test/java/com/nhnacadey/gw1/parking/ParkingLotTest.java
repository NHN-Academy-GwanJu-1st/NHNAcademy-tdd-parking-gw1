package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.domain.User;
import com.nhnacadey.gw1.parking.exception.LackOfParkingSpaceException;
import com.nhnacadey.gw1.parking.exception.NotAllowedExitExceptioon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    ParkingLot parkingLot;
    User testUser;

    @BeforeEach
    void setUp() {
        parkingLot = parkingLot.getInstance();
        parkingLot.parkingSpaceClear();

        testUser = new User(1000);
    }

    @Test
    void enter_collectSpaceTest() {

        Car car = new Car(testUser);

        parkingLot.enter(car);
        ParkingSpace[] parkingSpaces = parkingLot.getParkingSpaces();

        assertThat(parkingSpaces[0]).isNotNull();
        assertThat(parkingSpaces[0].getCar()).isEqualTo(car);
        assertThat(parkingLot.getCurrentSpaceSize()).isEqualTo(1);

    }

    @Test
    void enter_parkingSpace_isFull_thenLackOfParkingSpaceException() {

        for (int i = 0; i < 10; i++) {
            parkingLot.enter(new Car(new User(i * 1000)));
        }

        assertThatThrownBy(() -> parkingLot.enter(new Car(testUser)))
                .isInstanceOf(LackOfParkingSpaceException.class)
                .hasMessageContaining("Lack of ParkingSpace");
    }


    @Test
    void exit_nothingSpace_thenNotAllowedExitException() {
        assertThatThrownBy(() -> parkingLot.exit(new Car(testUser)))
                .isInstanceOf(NotAllowedExitExceptioon.class)
                .hasMessageContaining("Not Allowed Exit");
    }

    @Test
    void exit_notExistCar_thenNotAllowedExitException() {
        Car existCar = new Car(testUser);
        parkingLot.enter(existCar);

        Car nonExistCar = new Car(testUser);

        assertThatThrownBy(() -> parkingLot.exit(nonExistCar))
                .isInstanceOf(NotAllowedExitExceptioon.class)
                .hasMessageContaining("Not Allowed Exit");

    }

    @Test
    void exit_properlyOutOfCar() {
        Car car = new Car(testUser);
        parkingLot.enter(car);

        ParkingSpace[] parkingSpaces = parkingLot.getParkingSpaces();
        assertNotNull(parkingSpaces[0]);

        parkingLot.exit(car);
        assertNull(parkingSpaces[0]);

    }
}