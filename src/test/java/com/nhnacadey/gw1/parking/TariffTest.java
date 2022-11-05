package com.nhnacadey.gw1.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class TariffTest {

    Map<Integer, Long> priceMap;

    @BeforeEach
    void setUp() {
        priceMap = new HashMap<>();
        priceMap.put(1800, 1_000L);   // 30분
        priceMap.put(1801, 1_500L);   // 30분 1초
        priceMap.put(3000, 2_000L);   // 50분
        priceMap.put(3660, 3_000L);   // 61분
        priceMap.put(21600, 10_000L); // 6시간
    }

    @ParameterizedTest
    @ValueSource(ints = {1800, 1801, 3000, 3660, 21600})
    void tariff_payment_eachTimePrice(int timeSec) {

        Tariff tariff = new Tariff(timeSec);

        assertThat(tariff.payment()).isEqualTo(priceMap.get(timeSec));

    }
}