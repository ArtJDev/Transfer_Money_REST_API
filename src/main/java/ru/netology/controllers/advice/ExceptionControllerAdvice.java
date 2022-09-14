package ru.netology.controllers.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.exceptions.CardInvalidCvvException;
import ru.netology.exceptions.CardInvalidDateException;
import ru.netology.exceptions.CardNumberNotFoundException;
import ru.netology.exceptions.NotEnoughMoneyException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(CardNumberNotFoundException.class)
    public ResponseEntity<String> cardNumberNotFoundException(CardNumberNotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<String> notEnoughMoneyException(NotEnoughMoneyException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(CardInvalidDateException.class)
    public ResponseEntity<String> cardInvalidDateException(CardInvalidDateException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(CardInvalidCvvException.class)
    public ResponseEntity<String> cardNumberNotFoundException(CardInvalidCvvException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}