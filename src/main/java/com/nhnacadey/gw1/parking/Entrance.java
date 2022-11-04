package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.exception.LackOfParkingSpaceException;

import java.time.LocalDateTime;

public class Entrance {

    public Car scan(Car car) {
        /* 여기서 차량 번호를 받아 스캔하는 역할을 한다 .*/
        /* 차량 번호를 저장*/


        return car;

    }


    public ParkingSpace searchParkingSpace(Car car, ParkingSpace[] parkingSpaces, int currentSpaceSize, int spaceMaxSize) {

        int spaceNumber = 0;

        if (currentSpaceSize > spaceMaxSize) {
            throw new LackOfParkingSpaceException(currentSpaceSize);
        }

        for (int i = 0; i < parkingSpaces.length; i++) {
            if (parkingSpaces[i] == null) {
                spaceNumber = i;
                break;
            }
        }

        return fillParkingSpace(spaceNumber, car);
    }

    private ParkingSpace fillParkingSpace(int spaceNumber, Car car) {
        String code = "A-" + Integer.toString(spaceNumber + 1);
        return new ParkingSpace(code, car);
    }

}
