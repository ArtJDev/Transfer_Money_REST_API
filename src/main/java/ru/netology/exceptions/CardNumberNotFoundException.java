package ru.netology.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CardNumberNotFoundException extends RuntimeException {
    private final String message;
    private final ResponseEntity<String> response;

    public CardNumberNotFoundException(String message) {
        this.message = message;
        this.response = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
