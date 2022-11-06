package com.nhnacadey.gw1.parking.exception;

import com.nhnacadey.gw1.parking.domain.CarType;

public class NotAllowedCarTypeException extends RuntimeException {
    public NotAllowedCarTypeException(CarType carType) {
        super("Not Allowed CarType in ParkingSpace : " + carType);
    }
}
