package com.nhnacadey.gw1.parking.domain;

public enum Coupon {
    NONE(0),
    ONE_HOUR(3600),
    TWO_HOUR(7200);


    private final long discountTime;
    Coupon(long discountTime) {
        this.discountTime = discountTime;
    }

    public long discount(long exitTime) {
        return exitTime - this.discountTime;
    }
}
