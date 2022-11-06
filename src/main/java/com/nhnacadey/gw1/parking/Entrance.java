package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.domain.CarType;
import com.nhnacadey.gw1.parking.exception.LackOfParkingSpaceException;
import com.nhnacadey.gw1.parking.exception.NotAllowedCarTypeException;

import java.time.LocalDateTime;

public class Entrance {

    private final Car car;

    public Entrance(Car car) {
        this.car = car;
    }

    public Car scan() {

        if (isLargeCar(car)) {
            throw new NotAllowedCarTypeException(car.getCarType());
        }

        car.checkEntrance();
        return car;
    }

    private boolean isLargeCar(Car car) {
        return car.getCarType().equals(CarType.LARGE);
    }

}
