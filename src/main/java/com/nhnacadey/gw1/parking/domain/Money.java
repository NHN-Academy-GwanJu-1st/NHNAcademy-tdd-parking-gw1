package com.nhnacadey.gw1.parking.domain;

import com.nhnacadey.gw1.parking.exception.InvalidAmountException;

public class Money {

    private final long amount;

    public Money(long amount) {
        if (amount < 0) {
            throw new InvalidAmountException(amount);
        }
        this.amount = amount;
    }
}
