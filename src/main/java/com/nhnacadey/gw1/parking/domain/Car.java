package com.nhnacadey.gw1.parking.domain;

public class Car {

    private static long number;
    private final User user;

    public Car(User user) {
        this.number++;
        this.user = user;
    }

    public long getNumber() {
        return this.number;
    }

    public User getUser() {
        return this.user;
    }
}
