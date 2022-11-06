package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {


    private static ParkingSystem parkingSystem;

    private final ParkingLot parkingLot;
    private List<User> users;

    private ParkingSystem(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.users = new ArrayList<>();
    }

    private ParkingSystem getInstance(ParkingLot parkingLot) {
        if (this == null) {
            return new ParkingSystem(parkingLot);
        }
        return parkingSystem;
    }

    public void parking(Car car) {

    }


    // 얘는 여러 객체게 만들어지면 안되니까 싱글톤으로 관리한다
    // 파킹시스템에 등록되는 시간부터 시작을 측정한다?
    // 근데 시간은 파라미터라이즈드 테스트로 파라미터값을 이용해서 테스트 하는거 같은데


    // 들어오면 ParkingSystem에서 등록되고 나갈때는 빠져나가게? 그정도만 구현?
}
