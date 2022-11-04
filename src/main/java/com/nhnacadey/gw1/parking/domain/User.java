package com.nhnacadey.gw1.parking.domain;

import com.nhnacadey.gw1.parking.domain.Money;

public class User {

    private static long userId = 0;
    private final Money amount;

    public User(long amount) {
        this.userId++;
        this.amount = new Money(amount);
    }

    public long getUserId() {
        return this.userId;
    }

    public Money getAmount() {
        return this.amount;
    }
}
