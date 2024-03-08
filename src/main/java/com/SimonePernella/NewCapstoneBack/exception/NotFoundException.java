package com.SimonePernella.NewCapstoneBack.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("There is no item with this id: " + id);
    }

    public NotFoundException(String message) {
        super(message);
    }


}

