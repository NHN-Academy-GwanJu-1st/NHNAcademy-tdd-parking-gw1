package com.nhnacadey.gw1.parking;

public class ZeroBaseTariff extends Tariff {

    public ZeroBaseTariff(long totalTimeSec) {
        super(totalTimeSec);
    }

    @Override
    public long payment() {
        return super.payment();
    }
}
