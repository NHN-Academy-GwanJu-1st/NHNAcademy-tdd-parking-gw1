package com.nhnacadey.gw1.parking.domain;

import com.nhnacadey.gw1.parking.domain.Money;

public class User {

    private static long userId = 0;
    private final Money money;

    public User(long money) {
        this.userId++;
        this.money = new Money(money);
    }

    public long getUserId() {
        return this.userId;
    }

    public long getMoneyOfAmount() {
        return this.money.getAmount();
    }


    @Override
    public String toString() {
        return "User{" +
                "userId="+userId+
                "amount=" + money +
                '}';
    }
}
