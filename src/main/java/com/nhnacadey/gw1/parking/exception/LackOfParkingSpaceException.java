package com.nhnacadey.gw1.parking.exception;

public class LackOfParkingSpaceException extends RuntimeException {
    public LackOfParkingSpaceException(int currentSpaceSize) {
        super("Lack of ParkingSpace : " + currentSpaceSize);
    }
}
