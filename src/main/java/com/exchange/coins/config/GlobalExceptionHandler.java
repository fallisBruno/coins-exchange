package com.exchange.coins.config;

import com.exchange.coins.exceptions.CoinsNotConfiguredException;
import com.exchange.coins.exceptions.MachineOutOfCoinsException;
import com.exchange.coins.exceptions.NotEnoughCoinsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotEnoughCoinsException.class})
    public ResponseEntity<Object> handleNotEnoughCoinsException(NotEnoughCoinsException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({CoinsNotConfiguredException.class})
    public ResponseEntity<Object> handleCoinsNotConfiguredException(CoinsNotConfiguredException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({MachineOutOfCoinsException.class})
    public ResponseEntity<Object> handleCoinsMachineOutOfCoinsException(MachineOutOfCoinsException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

}
