package com.nhnacadey.gw1.parking.tariff;

public class AfterChangedTariff implements Tariff {

    private static int FIRST_30_MINUTES = 1800;
    private static int EXTRA_10_MINUTES = 600;
    private static int ONE_HOUR_SEC = 3600;
    private static int DAILY_MAXIMUM_SEC = 20400;
    private static int ONE_DAY = 86400;
    private static int BASE_PRICE = 1000;
    private static int EXTRA_PRICE = 500;
    private static int MAXIMUM_PRICE = 15_000;

    @Override
    public long payment(long totalTimeSec) {

        long resultPrice = 0;

        if (totalTimeSec <= FIRST_30_MINUTES) {
            return resultPrice;
        }

        if (totalTimeSec <= ONE_HOUR_SEC) {
            return BASE_PRICE;
        }

        long parkingDays = totalTimeSec / ONE_DAY;

        if (parkingDays > 0) {
            resultPrice = parkingDays * MAXIMUM_PRICE;
        }

        long parkingMinutes = totalTimeSec % ONE_DAY;

        if (parkingMinutes == 0) {
            return resultPrice;
        }

        if (parkingMinutes > DAILY_MAXIMUM_SEC) {
            resultPrice += MAXIMUM_PRICE;
            return resultPrice;
        }

        resultPrice += BASE_PRICE;

        parkingMinutes -= ONE_HOUR_SEC;
        resultPrice += EXTRA_PRICE * (parkingMinutes / EXTRA_10_MINUTES);
        if (parkingMinutes % EXTRA_10_MINUTES > 0) {
            resultPrice += EXTRA_PRICE;
        }

        return resultPrice;
    }
}
