package com.exchange.coins.domain;

import com.exchange.coins.exceptions.NotEnoughCoinsException;
import com.exchange.coins.persist.Coin;
import com.exchange.coins.persist.CoinsCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ExchangeServiceTest {

    @InjectMocks
    private ExchangeService victim;

    @BeforeEach
    public void setUp() throws Exception {
        Field cacheField = CoinsCache.class.getDeclaredField("CACHE");
        cacheField.setAccessible(true);

        @SuppressWarnings("unchecked")
        LinkedList<Coin> cache = (LinkedList<Coin>) cacheField.get(null);
        cache.clear();
        cache.add(new Coin(0.25, 100));
        cache.add(new Coin(0.10, 100));
        cache.add(new Coin(0.05, 100));
        cache.add(new Coin(0.01, 100));
    }

    @Test
    public void shouldReturnAmountOfCoinsSucessfully() throws NotEnoughCoinsException {
        Integer userMockInput = 30;

        Map<Double, Integer> cents =
                victim.exchange(userMockInput);

        Assertions.assertEquals(100, cents.get(0.25));
        Assertions.assertEquals(50, cents.get(0.10));
        Assertions.assertEquals(0, cents.get(0.05));
        Assertions.assertEquals(0, cents.get(0.01));
    }

    @Test
    public void shouldThrowNotEnoughCoinsException() {
        Integer userMockInput = 60;

        NotEnoughCoinsException exception =
                Assertions.assertThrows(NotEnoughCoinsException.class, () -> victim.exchange(userMockInput));

        Assertions.assertEquals("Not enough coins for $" + userMockInput, exception.getMessage());
    }

}
