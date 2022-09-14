package ru.netology.exceptions;

public class CardInvalidCvvException extends RuntimeException {
    private final String message;

    public CardInvalidCvvException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}