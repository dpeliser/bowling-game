package com.bowling.domain.runner;

import com.bowling.domain.model.BowlingMove;

import java.util.List;

/**
 * Represents a {@link Bowling} game to play.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.1
 */
public interface BowlingGame {

    /**
     * Plays a {@link Bowling} match.
     *
     * @param bowling      to play
     * @param bowlingMoves of the players
     */
    void play(final Bowling bowling, final List<BowlingMove> bowlingMoves);

}
