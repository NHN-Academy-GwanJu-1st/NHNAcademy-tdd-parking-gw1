package com.nhnacadey.gw1.parking.exception;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(long amount) {
        super("Invalid Amount Exception : " + amount);
    }
}
