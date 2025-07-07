package com.exchange.coins.domain;

import com.exchange.coins.exceptions.MachineOutOfCoinsException;
import com.exchange.coins.exceptions.NotEnoughCoinsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ExchangeController {

    ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchange")
    public Map<Double, Integer> exchange(@RequestParam(
            "moneyInput") Integer moneyInput)
            throws NotEnoughCoinsException, MachineOutOfCoinsException {
        return exchangeService.exchange(moneyInput);
    }

}
