package ru.netology.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CardInvalidCvvException {
    public ResponseEntity<String> exception (String message){
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}