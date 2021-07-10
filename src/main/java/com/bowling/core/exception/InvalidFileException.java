package com.bowling.core.exception;

/**
 * Exception for when a file is invalid for the a bowling match.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvalidFileException extends RuntimeException {

    /**
     * @param message for the exception
     */
    public InvalidFileException(final String message) {
        super(message);
    }

}
