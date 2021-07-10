package com.bowling.core.exception;

/**
 * Exception for when the parameter to run the application is invalid.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvalidParameterException extends RuntimeException {

    /**
     * @param message for the exception
     */
    public InvalidParameterException(final String message) {
        super(message);
    }

}
