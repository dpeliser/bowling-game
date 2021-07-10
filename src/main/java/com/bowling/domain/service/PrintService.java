package com.bowling.domain.service;

import com.bowling.domain.runner.Bowling;

/**
 * Service to print the result of a {@link Bowling} match.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
public interface PrintService {

    /**
     * Prints the result of a {@link Bowling}.
     *
     * @param bowling to print the result.
     */
    void print(final Bowling bowling);

}
