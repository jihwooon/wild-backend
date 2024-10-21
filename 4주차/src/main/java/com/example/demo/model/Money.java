package com.example.demo.model;

public record Money(
        int amount,
        String current
) {

    public static final Money ZERO = new Money(0, "KRW");

    public Money plus(Money other) {
        if (current.equals(other.current)) {
            throw new IllegalArgumentException("통화가 다릅니다.");
        }
        return new Money(amount + other.amount, this.current);
    }

    public Money multiply(int quantity) {
        return new Money(amount * quantity, current);
    }
}
