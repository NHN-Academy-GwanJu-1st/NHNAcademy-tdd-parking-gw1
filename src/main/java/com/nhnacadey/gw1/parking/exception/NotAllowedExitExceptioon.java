package com.nhnacadey.gw1.parking.exception;

public class NotAllowedExitExceptioon extends RuntimeException{
    public NotAllowedExitExceptioon(long carNumber) {
        super("Not Allowed Exit : " + carNumber);
    }
}
