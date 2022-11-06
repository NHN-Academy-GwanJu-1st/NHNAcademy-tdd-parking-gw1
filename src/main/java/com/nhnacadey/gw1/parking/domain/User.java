package com.nhnacadey.gw1.parking.domain;


public class User {

    private static long userId = 0;
    private Money money;
    private Payco payco;
    private Coupon coupon;


    public User(long money) {
        this.userId++;
        this.money = new Money(money);
        this.payco = Payco.NONE;
        this.coupon = Coupon.NONE;
    }

    public User(long money, Payco payco, Coupon coupon) {
        this.userId++;
        this.money = new Money(money);
        this.payco = payco;
        this.coupon = coupon;
    }

    public long getMoneyOfAmount() {
        return this.money.getAmount();
    }

    public void amountAfterPayment(long afterAmount) {
        this.money = new Money(afterAmount);
    }

    public Payco getPayco() {
        return payco;
    }

    public Coupon getCoupon() {
        return coupon;
    }

}
