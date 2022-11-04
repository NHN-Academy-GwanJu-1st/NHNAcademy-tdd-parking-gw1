package com.nhnacadey.gw1.parking.domain;

import com.nhnacadey.gw1.parking.exception.InvalidAmountException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {


    @Test
    @DisplayName("현재 돈이 마이너스인 경우")
    void amount_negative_thenInvalidAmountException() {
        long amount = -1;

        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(InvalidAmountException.class)
                .hasMessageContaining("Invalid Amount Exception");

    }
}