package ru.pentragon.spring1l7.spring1l7.exeptions;

public class NoAuthException extends RuntimeException {
    public NoAuthException(String message) {
        super(message);
    }
}
