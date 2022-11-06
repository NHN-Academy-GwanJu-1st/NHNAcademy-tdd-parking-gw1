package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.domain.CarType;
import com.nhnacadey.gw1.parking.domain.Money;
import com.nhnacadey.gw1.parking.domain.Payco;
import com.nhnacadey.gw1.parking.exception.InvalidAmountException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Exit {

    private final Car car;

    public Exit(Car car) {
        this.car = car;
    }

    public void pay(long exitTime) {

        car.setExitTime(exitTime);
        Tariff tariff = new Tariff(car.getExitTime());
        long payment = tariff.payment();
        log.info("할인 전 주차비 : {}", payment);

        payment = applyDiscountPolicy(payment);

        log.info("할인 후 주차비 : {}", payment);

        long userMoney = car.getUser().getMoneyOfAmount();
        log.info("유저가 갖고있는 돈 : {}", userMoney);

        if (userMoney < payment) {
            throw new InvalidAmountException(userMoney);
        }

        car.getUser().amountAfterPayment(userMoney - payment);
    }

    private long applyDiscountPolicy(long payment) {
        if (isSmallCar()) {
            payment -= payment * 0.1;
        }

        if (isPaycoMember()) {
            payment -= payment * 0.1;
        }
        return payment;
    }

    private boolean isPaycoMember() {
        return this.car.getUser().getPayco().equals(Payco.MEMBER);
    }

    private boolean isSmallCar() {
        return car.getCarType().equals(CarType.SMALL);
    }
}
