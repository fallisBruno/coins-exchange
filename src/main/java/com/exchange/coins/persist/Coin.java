package com.exchange.coins.persist;

public class Coin {

    private Double coin;
    private Integer amount;

    public Coin(Double coinValue, Integer amount) {
        this.coin = coinValue;
        this.amount = amount;
    }

    public Double getCoin() {
        return coin;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
