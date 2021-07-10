package com.bowling.core.exception;

/**
 * Exception for when a bowling match is invalid.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvalidBowlingMatchException extends RuntimeException {

    /**
     * @param message for the exception
     */
    public InvalidBowlingMatchException(final String message) {
        super(message);
    }

}
