package com.bowling.domain.validator;

import com.bowling.core.exception.InvalidBowlingMatchException;
import com.bowling.domain.runner.Bowling;
import lombok.var;
import org.springframework.stereotype.Component;

import static com.bowling.domain.utils.ErrorMessages.INVALID_BOWLING_MATCH_PLAYER_MUST_HAVE_10_FRAMES;
import static com.bowling.domain.utils.ErrorMessages.INVALID_BOWLING_MATCH_WITH_NO_PLAYERS;

/**
 * Validator for a {@link Bowling} match.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class BowlingValidator {

    /**
     * Validates if the given bowling match is valid.
     *
     * @param bowling to validate if is a valid match
     */
    public void validate(final Bowling bowling) {
        var players = bowling.getPlayers();

        if (players.isEmpty()) {
            throw new InvalidBowlingMatchException(INVALID_BOWLING_MATCH_WITH_NO_PLAYERS);
        }

        players.forEach(player -> {
            var framesSize = player.getFrames().size();
            if (framesSize != 10) {
                throw new InvalidBowlingMatchException(String.format(
                    INVALID_BOWLING_MATCH_PLAYER_MUST_HAVE_10_FRAMES,
                    player.getName(),
                    framesSize
                ));
            }
        });
    }

}
