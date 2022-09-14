package ru.netology.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CardNumberNotFoundException extends RuntimeException {
    private final String message;

    public CardNumberNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
