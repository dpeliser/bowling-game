package com.bowling.domain.runner;

/**
 * Calculates the score of a {@link Bowling} game.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.1
 */
public interface BowlingScore {

    /**
     * Calculates the score of a {@link Bowling} game.
     *
     * @param bowling to calculate the score
     */
    void calculate(final Bowling bowling);

}
