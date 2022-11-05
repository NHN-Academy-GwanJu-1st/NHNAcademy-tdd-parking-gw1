package com.nhnacadey.gw1.parking.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Car {

    private static long number = 0;
    private final User user;
    private long entranceTime;
    private long exitTime;
    public Car(User user) {
        this.number++;
        this.user = user;
        this.entranceTime = 0;
        this.exitTime = 0;
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

    public long getEntranceTime() {
        return entranceTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                "user=" + user +
                ", entranceTime=" + entranceTime +
                ", exitTime=" + exitTime +
                '}';
    }
}
