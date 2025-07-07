package com.exchange.coins.exceptions;

public class NotEnoughCoinsException extends Throwable {

    public NotEnoughCoinsException(Integer exchangeInput) {
        super("Not enough coins for $"+exchangeInput);
    }
}
