package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;

public class Exit {

    public Exit(Car car) {

    }

    public void pay(Car car) {

        /*
        * Car의 손님의 돈이 없으면 Exception
        *   최초 30분	1,000원
            추가 10분	500원	1초라도 넘으면 부과됩니다.
            일일 주차	10,000원	일 최대 금액입니다. 24:00이 넘어가면 추가 요금이 부과됩니다.
            2일 연속 주차 시 20,000원
            30분 1초 주차한 경우 요금은 1,500원입니다.
            50분 주차한 경우 요금은 2,000원입니다.
            61분 주차한 경우 요금은 3,000원입니다.
            6시간 주차한 경우 요금은 10,000원입니다.
        * */

    }
}
