package com.exchange.coins.exceptions;

public class MachineOutOfCoinsException extends Throwable {

    public MachineOutOfCoinsException() {
        super("Machine out of coins!");
    }
}
