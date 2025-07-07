package com.exchange.coins.domain;

import com.exchange.coins.persist.Coin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Exchange {

    public static Map<Double, Integer> calculateCents(Integer moneyInput, LinkedList<Coin> coins) {
        Map<Double, Integer> resultMap = new HashMap<>();

        for (Coin cent : coins) {
            double centValue = cent.getCoin();
            double centsAmount = Math.min(moneyInput / centValue, cent.getAmount());
            resultMap.put(centValue, (int) centsAmount);
            moneyInput = moneyInput - (int) (centsAmount * centValue);
        }

        return resultMap;
    }

    public static Double maximumExchangePossible(LinkedList<Coin> cachedCoins) {
        return cachedCoins.stream()
                .map(c -> c.getCoin() * c.getAmount())
                .reduce(0.0, Double::sum);
    }
}
