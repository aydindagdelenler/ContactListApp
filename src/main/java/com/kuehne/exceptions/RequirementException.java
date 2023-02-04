package com.kuehne.exceptions;

/**
 * A custom exception to be thrown if there is an error
 */
public class RequirementException extends RuntimeException {

    public RequirementException(ExceptionCode exceptionCode) {
        super(exceptionCode.getDescription());
    }
}
