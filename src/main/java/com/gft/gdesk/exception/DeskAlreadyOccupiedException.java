package com.gft.gdesk.exception;

public class DeskAlreadyOccupiedException extends RuntimeException {
    public DeskAlreadyOccupiedException(String message) {
        super(message);
    }
}
