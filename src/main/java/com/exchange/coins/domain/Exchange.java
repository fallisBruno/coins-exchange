package com.exchange.coins.domain;

import com.exchange.coins.exceptions.CoinsNotConfiguredException;
import com.exchange.coins.exceptions.NotEnoughCoinsException;
import com.exchange.coins.persist.Coin;
import com.exchange.coins.persist.CoinsCache;

import java.util.*;

public class Exchange {

    public static Map<Double, Integer> getCents(Integer moneyInput) throws CoinsNotConfiguredException, NotEnoughCoinsException {

        LinkedList<Coin> cachedCoins = CoinsCache.getCoins();

        if (moneyInput > maximumExchangePossible(cachedCoins)) throw new NotEnoughCoinsException(moneyInput);

        Map<Double, Integer> centsResultMap = calculateCents(moneyInput, cachedCoins);

        CoinsCache.updateWithdrawCoins(centsResultMap);

        return centsResultMap;
    }

    private static Map<Double, Integer> calculateCents(Integer moneyInput, LinkedList<Coin> coins) {

        Map<Double, Integer> resultMap = new HashMap<>();

        for (Coin cent : coins) {
            double centValue = cent.getCoin();
            double centsAmount = Math.min(moneyInput / centValue, cent.getAmount());
            resultMap.put(centValue, (int) centsAmount);
            moneyInput = moneyInput - (int) (centsAmount * centValue);
        }

        return resultMap;
    }

    private static Double maximumExchangePossible(LinkedList<Coin> cachedCoins){
        return cachedCoins.stream()
                .map(c -> c.getCoin() * c.getAmount())
                .reduce(0.0, Double::sum);
    }
}
