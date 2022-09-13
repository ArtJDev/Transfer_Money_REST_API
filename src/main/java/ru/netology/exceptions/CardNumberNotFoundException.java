package ru.netology.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CardNumberNotFoundException extends RuntimeException {
    private final String message;

    public CardNumberNotFoundException(String message) {
        this(new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST));
    }

    public ResponseEntity<String> exception (String message){
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
