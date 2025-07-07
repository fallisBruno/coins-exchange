package com.exchange.coins.domain;

import com.exchange.coins.exceptions.MachineOutOfCoinsException;
import com.exchange.coins.exceptions.NotEnoughCoinsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ExchangeController {

    ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchange")
    public Map<Double, Integer> exchange(@RequestParam(value = "moneyInput", defaultValue = "0") Integer moneyInput)
            throws NotEnoughCoinsException, MachineOutOfCoinsException {
        return exchangeService.exchange(moneyInput);
    }

}
