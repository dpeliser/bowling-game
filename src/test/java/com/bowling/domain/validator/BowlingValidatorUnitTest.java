package com.bowling.domain.validator;

import com.bowling.core.exception.InvalidBowlingMatchException;
import com.bowling.domain.model.BowlingMove;
import com.bowling.domain.runner.Bowling;
import com.bowling.game.BowlingGameImpl;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

import static com.bowling.domain.utils.Constants.TAB;
import static com.bowling.domain.utils.ErrorMessages.INVALID_BOWLING_MATCH_PLAYER_MUST_HAVE_10_FRAMES;
import static com.bowling.domain.utils.ErrorMessages.INVALID_BOWLING_MATCH_WITH_NO_PLAYERS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link BowlingValidator} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
public class BowlingValidatorUnitTest {

    @InjectMocks
    private BowlingGameImpl bowlingGame;

    @InjectMocks
    private BowlingValidator validator;

    @Test
    public void validateShouldThrowInvalidBowlingMatchExceptionIfPlayersIsEmpty() {
        // Given
        var bowling = new Bowling();
        bowlingGame.play(bowling, Collections.emptyList());

        // When and Then
        var exception = assertThrows(InvalidBowlingMatchException.class, () -> validator.validate(bowling));
        assertEquals(INVALID_BOWLING_MATCH_WITH_NO_PLAYERS, exception.getMessage());
    }

    @Test
    public void validateShouldThrowInvalidBowlingMatchExceptionIfAPlayersHasMoreThan10Frames() throws IOException {
        // Given
        var args = "src/test/resources/negative/extra-score.txt";
        var file = new File(args);
        var bowlingMoves = new ArrayList<BowlingMove>();
        try (var lines = Files.lines(file.toPath())) {
            lines.forEach(line -> {
                var columns = line.split(TAB);
                bowlingMoves.add(new BowlingMove(columns[0], columns[1]));
            });
        }
        var bowling = new Bowling();
        bowlingGame.play(bowling, bowlingMoves);

        // When and Then
        var exception = assertThrows(InvalidBowlingMatchException.class, () -> validator.validate(bowling));
        assertEquals(
            String.format(
                INVALID_BOWLING_MATCH_PLAYER_MUST_HAVE_10_FRAMES,
                "Carl",
                11
            ),
            exception.getMessage()
        );
    }

    @Test
    public void validateShouldThrowInvalidBowlingMatchExceptionIfAPlayersHasLessThan10Frames() throws IOException {
        // Given
        var args = "src/test/resources/negative/missing-score.txt";
        var file = new File(args);
        var bowlingMoves = new ArrayList<BowlingMove>();
        try (var lines = Files.lines(file.toPath())) {
            lines.forEach(line -> {
                var columns = line.split(TAB);
                bowlingMoves.add(new BowlingMove(columns[0], columns[1]));
            });
        }
        var bowling = new Bowling();
        bowlingGame.play(bowling, bowlingMoves);

        // When and Then
        var exception = assertThrows(InvalidBowlingMatchException.class, () -> validator.validate(bowling));
        assertEquals(
            String.format(
                INVALID_BOWLING_MATCH_PLAYER_MUST_HAVE_10_FRAMES,
                "Carl",
                9
            ),
            exception.getMessage()
        );
    }

    @Test
    public void validateShouldNowThrowInvalidBowlingMatchExceptionIfMatchIsValid() throws IOException {
        // Given
        var args = "src/test/resources/positive/scores.txt";
        var file = new File(args);
        var bowlingMoves = new ArrayList<BowlingMove>();
        try (var lines = Files.lines(file.toPath())) {
            lines.forEach(line -> {
                var columns = line.split(TAB);
                bowlingMoves.add(new BowlingMove(columns[0], columns[1]));
            });
        }
        var bowling = new Bowling();
        bowlingGame.play(bowling, bowlingMoves);

        // When and Then
        validator.validate(bowling);
    }

}
