package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.domain.Money;
import com.nhnacadey.gw1.parking.exception.InvalidAmountException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Exit {

    private final Car car;

    public Exit(Car car) {
        this.car = car;
    }

    public void pay(long exitTime) {

//      여기서 페이코 회원 체크 해줘서 넘겨주자

        car.setExitTime(exitTime);
        Tariff tariff = new Tariff(car.getExitTime());
        long payment = tariff.payment();
        log.info("주차비 : {}", payment);

        long userMoney = car.getUser().getMoneyOfAmount();
        log.info("유저가 갖고있는 돈 : {}", userMoney);
        if (userMoney < payment) {
            throw new InvalidAmountException(userMoney);
        }


        car.getUser().amountAfterPayment(userMoney - payment);


    }
}
