package ru.netology.exceptions;

public class CardInvalidDateException extends RuntimeException {
    private final String message;

    public CardInvalidDateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}