package com.nhnacadey.gw1.parking;

import com.nhnacadey.gw1.parking.tariff.AfterChangedTariff;
import com.nhnacadey.gw1.parking.tariff.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AfterChangedTariffTest {

    Map<Integer, Long> priceMap;

    @BeforeEach
    void setUp() {
        priceMap = new HashMap<>();
        priceMap.put(1800, 0L);   // 30분
        priceMap.put(1801, 1_000L);   // 30분 1초
        priceMap.put(3000, 1_000L);   // 50분
        priceMap.put(3660, 1_500L);   // 61분
        priceMap.put(21600, 15_000L); // 6시간
        // 60분 * 6 = 360분 60분 1천원 80분 2천 100 3천 120 4 140 5 160 6 180 7 200 8 220 9 240 100
        // 260분에 1.1 280분 1.2 300분 1.3 320분 1.4 340분 1.5
    }

    @ParameterizedTest
    @ValueSource(ints = {1800, 1801, 3000, 3660, 21600})
    void tariff_payment_eachTimePrice(int timeSec) {

        Tariff tariff = new AfterChangedTariff();

        assertThat(tariff.payment(timeSec)).isEqualTo(priceMap.get(timeSec));

    }

}