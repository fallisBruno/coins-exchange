package com.exchange.coins.exceptions;

public class CoinsNotConfiguredException extends Throwable {

    public CoinsNotConfiguredException() {
        super("Coins not configured, machine is out!");
    }
}
