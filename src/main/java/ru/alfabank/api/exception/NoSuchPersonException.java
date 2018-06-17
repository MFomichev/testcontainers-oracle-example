package ru.alfabank.api.exception;

public class NoSuchPersonException extends RuntimeException {
    public NoSuchPersonException(String message) {
        super(message);
    }
}
