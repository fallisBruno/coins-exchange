package com.exchange.coins.persist;

import com.exchange.coins.exceptions.CoinsNotConfiguredException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Map;

public class CoinsCache {

    private static final LinkedList<Coin> CACHE = new LinkedList<>();

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Integer> tempMap = mapper.readValue(
                    new File("src/main/resources/coins.json"),
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, Integer.class)
            );

            for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
                Double coinValue = Double.parseDouble(entry.getKey());
                Integer amount = entry.getValue();
                CACHE.add(new Coin(coinValue, amount));
            }

            if(CACHE.isEmpty()) throw new CoinsNotConfiguredException();

        } catch (IOException | CoinsNotConfiguredException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startCache() {
        CACHE.forEach((coin) ->
                System.out.println(MessageFormat.format("Coin {0} initiated with {1} coins.", coin.getCoin(), coin.getAmount())));
        System.out.println("Cache started!");
    }

    public static LinkedList<Coin> getCoins() {
        return CACHE;
    }

    public static void updateWithdrawCoins(Map<Double, Integer> centsResultMap) {
        for (Coin coin : CACHE) {
            if (centsResultMap.containsKey(coin.getCoin())) {
                Integer newAmount = coin.getAmount() - centsResultMap.get(coin.getCoin());
                coin.setAmount(newAmount);
            }
        }
    }

}
