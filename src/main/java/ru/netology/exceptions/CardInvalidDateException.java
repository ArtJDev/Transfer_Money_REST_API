package ru.netology.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CardInvalidDateException {
    public ResponseEntity<String> exception (String message){
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}