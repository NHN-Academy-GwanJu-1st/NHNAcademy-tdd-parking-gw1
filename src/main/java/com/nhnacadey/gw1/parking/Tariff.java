package com.nhnacadey.gw1.parking;

public class Tariff {

    private static int FIRST_30_MINUTES = 1800;
    private static int EXTRA_10_MINUTES = 600;
    private static int MAXIMUM_ONE_DAY = 86400;

    private static int BASE_PRICE = 1000;
    private static int EXTRA_PRICE = 500;
    private static int MAXIMUM_PRICE = 10_000;

    private final long totalTimeSec;

    public Tariff(long totalTimeSec) {
        this.totalTimeSec = totalTimeSec;
    }

    public void payment() {

        /*
        * 최초 30분까지 무조건 1000원
        * 추가 10분당 500원 단, 1초라도 넘으면 500원씩 부과
        * 일일 최대 10_000원
        *
        *
        * */

        if (this.totalTimeSec < FIRST_30_MINUTES) {

        }
    }

}
