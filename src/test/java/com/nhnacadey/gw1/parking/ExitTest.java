package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.domain.CarType;
import com.nhnacadey.gw1.parking.domain.Payco;
import com.nhnacadey.gw1.parking.domain.User;
import com.nhnacadey.gw1.parking.exception.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExitTest {

    Car car;
    Exit exit;

    @BeforeEach
    void setUp() {
        car = new Car(new User(10_000), CarType.MEDIUM);
        exit = new Exit(car);
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

        assertThat(car.getUser().getMoneyOfAmount()).isEqualTo(8_000);

    }

    @Test
    void exit_smallCar_discount() {
        Car smallCar = new Car(new User(10_000), CarType.SMALL);

        Exit smallExit = new Exit(smallCar);

        smallExit.pay(21600);

        assertThat(smallCar.getUser().getMoneyOfAmount()).isEqualTo(1000);
    }

    @Test
    void exit_paycoMemeber_noSmallCar_discount() {
        User paycoMember = new User(10_000, Payco.MEMBER);
        Car paycoCar = new Car(paycoMember, CarType.MEDIUM);
        Exit paycoExit = new Exit(paycoCar);

        paycoExit.pay(21600);

        assertThat(paycoMember.getMoneyOfAmount()).isEqualTo(1_000);

    }
    @Test
    void exit_paycoMemeber_smallCar_discount() {
        User paycoMember = new User(10_000, Payco.MEMBER);
        Car paycoCar = new Car(paycoMember, CarType.SMALL);
        Exit paycoExit = new Exit(paycoCar);

        paycoExit.pay(21600);

        assertThat(paycoMember.getMoneyOfAmount()).isEqualTo(1_900);

    }

}