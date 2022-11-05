package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.exception.LackOfParkingSpaceException;

import java.time.LocalDateTime;

public class Entrance {

    public Car scan(Car car) {
        /* 여기서 차량 번호를 받아 스캔하는 역할을 한다 .*/
        /* 차량 번호를 저장*/
        car.checkEntrance();
        return car;
    }

}
