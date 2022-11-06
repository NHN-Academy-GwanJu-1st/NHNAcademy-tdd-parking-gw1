package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.domain.Car;
import com.nhnacadey.gw1.parking.exception.LackOfParkingSpaceException;
import com.nhnacadey.gw1.parking.exception.NotAllowedExitExceptioon;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParkingLot {

    private static ParkingLot parkingLot;
    private ParkingSpace[] parkingSpaces;
    private static int currentSpaceSize = 0;

    private int spaceMaxSize;

    private ParkingLot() {
        this.parkingSpaces = new ParkingSpace[10];

        spaceMaxSize = parkingSpaces.length;
    }

    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            return new ParkingLot();
        }
        return parkingLot;
    }

    public void parkingSpaceClear() {
        for (int i = 0; i < spaceMaxSize; i++) {
            parkingSpaces[i] = null;
        }
        currentSpaceSize = 0;
    }

    public ParkingSpace[] getParkingSpaces() {
        return parkingSpaces;
    }

    public int getCurrentSpaceSize() {
        return currentSpaceSize;
    }

    public void enter(Car car) {
        log.info("ParkingLot enter");

        if (currentSpaceSize >= spaceMaxSize) {
            throw new LackOfParkingSpaceException(currentSpaceSize);
        }

        Entrance entrance = new Entrance(car);
        Car scannedCar = entrance.scan();
        searchParkingSpace(scannedCar);
    }

    public void searchParkingSpace(Car car) {
        for (int i = 0; i < parkingSpaces.length; i++) {
            if (parkingSpaces[i] == null) {
                parkingSpaces[i] = fillParkingSpace(i, car);
                currentSpaceSize++;
                break;
            }
        }
    }

    private ParkingSpace fillParkingSpace(int spaceNumber, Car car) {
        String code = "A-" + Integer.toString(spaceNumber + 1);
        return new ParkingSpace(code, car);
    }

    public void exit(Car car) {
        log.info("Parking exit");

        Car exitCar;

        if (!isExistCar(car)) {
            throw new NotAllowedExitExceptioon(car.getNumber());
        }

        for (int i = 0; i < spaceMaxSize; i++) {
            searchAndExitCar(car, i);
        }
    }

    private void searchAndExitCar(Car car, int i) {
        Car exitCar;
        if (parkingSpaces[i] != null && parkingSpaces[i].getCar().equals(car)) {
            exitCar = parkingSpaces[i].getCar();
            parkingSpaces[i] = null;
            currentSpaceSize--;
        }
    }

    private boolean isExistCar(Car car) {

        if (currentSpaceSize == 0) {
            return false;
        }

        if (!car.isEntrance()) {
            return false;
        }

        return true;
    }
}
