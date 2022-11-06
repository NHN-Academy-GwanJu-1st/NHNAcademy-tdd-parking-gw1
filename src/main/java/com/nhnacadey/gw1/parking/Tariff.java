package com.nhnacadey.gw1.parking;

public class Tariff {

    private static int FIRST_30_MINUTES = 1800;
    private static int EXTRA_10_MINUTES = 600;
    private static int DAILY_MAXIMUM_SEC = 12000;
    private static int ONE_DAY = 86400;

    private static int BASE_PRICE = 1000;
    private static int EXTRA_PRICE = 500;
    private static int MAXIMUM_PRICE = 10_000;

    private final long totalTimeSec;

    public Tariff(long totalTimeSec) {
        this.totalTimeSec = totalTimeSec;
    }

    public long payment() {

        long resultPrice = 0;

        /* 몇일 주차 했는지 */
        long parkingDays = this.totalTimeSec / ONE_DAY;

        resultPrice = parkingDays * MAXIMUM_PRICE;

        /* 몇시간 주차 했는지 */
        long parkingMinutes = this.totalTimeSec % ONE_DAY;

        if (parkingMinutes == 0) {
            return resultPrice;
        }

        /* 기본요금 30분까지 주차한 경우 */
        if (parkingMinutes <= FIRST_30_MINUTES) {
            resultPrice += BASE_PRICE;
            return resultPrice;
        }

        /* 하루 최대요금인 200분 초과인 경우 */
        if (parkingMinutes > DAILY_MAXIMUM_SEC) {
            resultPrice += MAXIMUM_PRICE;
            return resultPrice;
        }

        /* 30분 보다 길게 주차한 경우 */
        resultPrice += BASE_PRICE;
        /* 기본 요금 시간 제외 */
        parkingMinutes -= FIRST_30_MINUTES;

        resultPrice += EXTRA_PRICE * (parkingMinutes / EXTRA_10_MINUTES);

        if (parkingMinutes % EXTRA_10_MINUTES > 0) {
            resultPrice += EXTRA_PRICE;
        }

        /* 고객이 돈이 부족한 경우 Exception */

        return resultPrice;
    }

}
