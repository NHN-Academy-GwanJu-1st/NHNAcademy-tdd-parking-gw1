package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;

public class ParkingSpace {

    private final String code;
    private final Car car;

    public ParkingSpace(String code, Car car) {
        this.code = code;
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}
