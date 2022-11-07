package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.*;
import com.nhnacadey.gw1.parking.exception.InvalidAmountException;
import com.nhnacadey.gw1.parking.tariff.AfterChangedTariff;
import com.nhnacadey.gw1.parking.tariff.BeforeChangedTariff;
import com.nhnacadey.gw1.parking.tariff.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ExitTest {

    Car car;
    Exit exit;
    Tariff tariff;

    @BeforeEach
    void setUp() {
        car = new Car(new User(10_000), CarType.MEDIUM);
        exit = new Exit(car);
        tariff = new BeforeChangedTariff();
    }

    @Test
    void exit_userMoney_notEnough_thenInvalidAmountException() {

        assertThatThrownBy(() -> exit.pay(86401, tariff))
                .isInstanceOf(InvalidAmountException.class)
                .hasMessageContaining("Invalid Amount Exception");

    }

    @Test
    void exit_userMoney_enough_successOut() {
        exit.pay(3000, tariff);

        assertThat(car.getUser().getMoneyOfAmount()).isEqualTo(8_000);

    }

    @Test
    void exit_smallCar_discount() {
        Car smallCar = new Car(new User(10_000), CarType.SMALL);

        Exit smallExit = new Exit(smallCar);

        smallExit.pay(21600, tariff);

        assertThat(smallCar.getUser().getMoneyOfAmount()).isEqualTo(1000);
    }

    @Test
    void exit_paycoMemeber_noSmallCar_discount() {
        User paycoMember = new User(10_000, Payco.MEMBER, Coupon.NONE);
        Car paycoCar = new Car(paycoMember, CarType.MEDIUM);
        Exit paycoExit = new Exit(paycoCar);

        paycoExit.pay(21600, tariff);

        assertThat(paycoMember.getMoneyOfAmount()).isEqualTo(1_000);

    }
    @Test
    void exit_paycoMemeber_smallCar_discount() {
        User paycoMember = new User(10_000, Payco.MEMBER, Coupon.TWO_HOUR);
        Car paycoCar = new Car(paycoMember, CarType.SMALL);
        Exit paycoExit = new Exit(paycoCar);

        paycoExit.pay(21600, tariff);

        assertThat(paycoMember.getMoneyOfAmount()).isEqualTo(1_900);

    }


    @Test
    void exit_coupon_twoHour() {
        User user = new User(10_000, Payco.NONE, Coupon.TWO_HOUR);
        exit = new Exit(new Car(user, CarType.MEDIUM));

        exit.pay(10800, new AfterChangedTariff());
        assertThat(user.getMoneyOfAmount()).isEqualTo(9_000);
    }

    @Test
    void exit_coupon_oneHour() {
        User user = new User(10_000, Payco.NONE, Coupon.ONE_HOUR);
        exit = new Exit(new Car(user, CarType.MEDIUM));

        exit.pay(3540, new AfterChangedTariff());
        assertThat(user.getMoneyOfAmount()).isEqualTo(10_000);
    }

    @Test
    void exit_AfterTariff_oneHourCoupon_smallCar() {
        User user = new User(10_000, Payco.NONE, Coupon.ONE_HOUR);
        exit = new Exit(new Car(user, CarType.SMALL));

        exit.pay(14000, new AfterChangedTariff());
        assertThat(user.getMoneyOfAmount()).isEqualTo(3700);
    }

    @Test
    void exit_AfterTariff_twoHourCoupon_smallCar_payco() {
        User user = new User(10_000, Payco.MEMBER, Coupon.TWO_HOUR);
        exit = new Exit(new Car(user, CarType.SMALL));

        exit.pay(14000, new AfterChangedTariff());
        assertThat(user.getMoneyOfAmount()).isEqualTo(6760);
    }


}