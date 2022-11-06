package com.nhnacadey.gw1.parking.domain;

import java.util.Objects;

public class Car {

    private static long number = 0;
    private final User user;
    private boolean entranceTime;
    private long exitTime;
    private CarType carType;

    public Car(User user, CarType carType) {
        this.number++;
        this.user = user;
        this.entranceTime = false;
        this.exitTime = 0;
        this.carType = carType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return entranceTime == car.entranceTime && exitTime == car.exitTime && Objects.equals(user, car.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, entranceTime, exitTime);
    }

    public long getNumber() {
        return this.number;
    }

    public User getUser() {
        return this.user;
    }

    public boolean isEntrance() {
        return this.entranceTime;
    }

    public void checkEntrance() {
        this.entranceTime = true;
    }

    public long getExitTime() {
        return exitTime;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

}
