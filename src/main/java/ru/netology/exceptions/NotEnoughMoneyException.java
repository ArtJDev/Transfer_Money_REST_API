package ru.netology.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    private final String message;

    public NotEnoughMoneyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
