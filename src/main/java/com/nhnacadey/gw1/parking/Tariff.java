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

        /*
        * 최초 30분까지 무조건 1000원
        * 추가 10분당 500원 단, 1초라도 넘으면 500원씩 부과
        * 일일 최대 10_000원
        * 아니구나 40분 까지 1500원 50분 2000원 60분 2500 70분 3000원 90분 4000원 110분 5000원 130분 6000원 150분 7000원 170분 8000원
        * 190분 9000원 200분까지 9500원 200분 1초부터 1만원
        * 즉, 200분이 넘어가면 무조건 하루 최대요금이 된다.
        * */
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
