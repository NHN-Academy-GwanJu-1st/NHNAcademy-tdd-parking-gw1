package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.domain.User;
import com.nhnacadey.gw1.parking.exception.InvalidAmountException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ExitTest {

    Car testCar;
    Exit exit;

    @BeforeEach
    void setUp() {
        testCar = new Car(new User(10_000));
        exit = new Exit(testCar);
    }

    @Test
    void exit_userMoney_notEnough_thenInvalidAmountException() {

        assertThatThrownBy(() -> exit.pay(86401))
                .isInstanceOf(InvalidAmountException.class)
                .hasMessageContaining("Invalid Amount Exception");

    }

    @Test
    void exit_userMoney_enough_successOut() {
        exit.pay(3000);

        assertThat(testCar.getUser().getMoneyOfAmount()).isEqualTo(8_000);

    }

}