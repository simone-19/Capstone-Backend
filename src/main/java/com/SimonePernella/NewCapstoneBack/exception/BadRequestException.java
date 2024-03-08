package com.SimonePernella.NewCapstoneBack.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {

    private List<ObjectError> errorList;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, List<ObjectError> errorList) {
        super(message);
        this.errorList = errorList;
    }
}
