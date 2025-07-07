package com.exchange.coins.domain;

import com.exchange.coins.exceptions.MachineOutOfCoinsException;
import com.exchange.coins.exceptions.NotEnoughCoinsException;
import com.exchange.coins.persist.Coin;
import com.exchange.coins.persist.CoinsCache;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;

@Service
public class ExchangeService {

    public Map<Double, Integer> exchange(Integer moneyInput)
            throws NotEnoughCoinsException, MachineOutOfCoinsException {

        LinkedList<Coin> cachedCoins = CoinsCache.getCoins();

        if (isValid(cachedCoins, moneyInput)) {
            Map<Double, Integer> centsResultMap =
                    Exchange.calculateCents(moneyInput, cachedCoins);

            CoinsCache.updateWithdrawCoins(centsResultMap);

            return centsResultMap;
        }

        return null;
    }

    private boolean isValid(LinkedList<Coin> cachedCoins, Integer moneyInput)
            throws NotEnoughCoinsException, MachineOutOfCoinsException {

        if (moneyInput > Exchange.maximumExchangePossible(cachedCoins))
            throw new NotEnoughCoinsException(moneyInput);

        boolean allAmountsZero =
                cachedCoins.stream().allMatch(coin -> coin.getAmount() <= 0);
        if (allAmountsZero)
            throw new MachineOutOfCoinsException();

        return true;
    }

}
