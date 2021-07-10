package com.bowling.game;

import com.bowling.domain.model.BowlingMove;
import com.bowling.domain.runner.Bowling;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static com.bowling.domain.utils.Constants.FOUL;
import static com.bowling.domain.utils.Constants.SPARE;
import static com.bowling.domain.utils.Constants.STRIKE;
import static com.bowling.domain.utils.Constants.TAB;
import static org.junit.Assert.assertEquals;

/**
 * {@link BowlingGameImpl} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class BowlingGameImplUnitTest {

    @InjectMocks
    private BowlingGameImpl bowlingGame;

    @Test
    public void shouldPlayBowling() throws IOException {
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

        // When
        bowlingGame.play(bowling, bowlingMoves);

        // Then
        var players = bowling.getPlayers();
        assertEquals(2, players.size());

        var jeff = players.get(0);
        assertEquals("Jeff", jeff.getName());
        assertEquals(10, jeff.getFrames().size());

        var jeffFrame0 = jeff.getFrames().get(0);
        assertEquals(STRIKE, jeffFrame0.getFirstScore());
        var jeffFrame1 = jeff.getFrames().get(1);
        assertEquals("7", jeffFrame1.getFirstScore());
        assertEquals(SPARE, jeffFrame1.getSecondScore());
        var jeffFrame2 = jeff.getFrames().get(2);
        assertEquals("9", jeffFrame2.getFirstScore());
        assertEquals("0", jeffFrame2.getSecondScore());
        var jeffFrame3 = jeff.getFrames().get(3);
        assertEquals(STRIKE, jeffFrame3.getFirstScore());
        var jeffFrame4 = jeff.getFrames().get(4);
        assertEquals("0", jeffFrame4.getFirstScore());
        assertEquals("8", jeffFrame4.getSecondScore());
        var jeffFrame5 = jeff.getFrames().get(5);
        assertEquals("8", jeffFrame5.getFirstScore());
        assertEquals(SPARE, jeffFrame5.getSecondScore());
        var jeffFrame6 = jeff.getFrames().get(6);
        assertEquals(FOUL, jeffFrame6.getFirstScore());
        assertEquals("6", jeffFrame6.getSecondScore());
        var jeffFrame7 = jeff.getFrames().get(7);
        assertEquals(STRIKE, jeffFrame7.getFirstScore());
        var jeffFrame8 = jeff.getFrames().get(8);
        assertEquals(STRIKE, jeffFrame8.getFirstScore());
        var jeffFrame9 = jeff.getFrames().get(9);
        assertEquals(STRIKE, jeffFrame9.getFirstScore());
        assertEquals("8", jeffFrame9.getSecondScore());
        assertEquals("1", jeffFrame9.getThirdScore());

        var john = players.get(1);
        assertEquals("John", players.get(1).getName());
        assertEquals(10, john.getFrames().size());

        var johnFrame0 = john.getFrames().get(0);
        assertEquals("3", johnFrame0.getFirstScore());
        assertEquals(SPARE, johnFrame0.getSecondScore());
        var johnFrame1 = john.getFrames().get(1);
        assertEquals("6", johnFrame1.getFirstScore());
        assertEquals("3", johnFrame1.getSecondScore());
        var johnFrame2 = john.getFrames().get(2);
        assertEquals(STRIKE, johnFrame2.getFirstScore());
        var johnFrame3 = john.getFrames().get(3);
        assertEquals("8", johnFrame3.getFirstScore());
        assertEquals("1", johnFrame3.getSecondScore());
        var johnFrame4 = john.getFrames().get(4);
        assertEquals(STRIKE, johnFrame4.getFirstScore());
        var johnFrame5 = john.getFrames().get(5);
        assertEquals(STRIKE, johnFrame5.getFirstScore());
        var johnFrame6 = john.getFrames().get(6);
        assertEquals("9", johnFrame6.getFirstScore());
        assertEquals("0", johnFrame6.getSecondScore());
        var johnFrame7 = john.getFrames().get(7);
        assertEquals("7", johnFrame7.getFirstScore());
        assertEquals(SPARE, johnFrame7.getSecondScore());
        var johnFrame8 = john.getFrames().get(8);
        assertEquals("4", johnFrame8.getFirstScore());
        assertEquals("4", johnFrame8.getSecondScore());
        var johnFrame9 = john.getFrames().get(9);
        assertEquals(STRIKE, johnFrame9.getFirstScore());
        assertEquals("9", johnFrame9.getSecondScore());
        assertEquals("0", johnFrame9.getThirdScore());
    }

    @Test
    public void shouldPlayBowlingWithPerfectScore() throws IOException {
        // Given
        var args = "src/test/resources/positive/perfect.txt";
        var file = new File(args);
        var bowlingMoves = new ArrayList<BowlingMove>();
        try (var lines = Files.lines(file.toPath())) {
            lines.forEach(line -> {
                var columns = line.split(TAB);
                bowlingMoves.add(new BowlingMove(columns[0], columns[1]));
            });
        }
        var bowling = new Bowling();

        // When
        bowlingGame.play(bowling, bowlingMoves);

        // Then
        var players = bowling.getPlayers();
        assertEquals(1, players.size());

        var carl = players.get(0);
        assertEquals("Carl", carl.getName());
        assertEquals(10, carl.getFrames().size());

        var carlFrame0 = carl.getFrames().get(0);
        assertEquals(STRIKE, carlFrame0.getFirstScore());
        var carlFrame1 = carl.getFrames().get(1);
        assertEquals(STRIKE, carlFrame1.getFirstScore());
        var carlFrame2 = carl.getFrames().get(2);
        assertEquals(STRIKE, carlFrame2.getFirstScore());
        var carlFrame3 = carl.getFrames().get(3);
        assertEquals(STRIKE, carlFrame3.getFirstScore());
        var carlFrame4 = carl.getFrames().get(4);
        assertEquals(STRIKE, carlFrame4.getFirstScore());
        var carlFrame5 = carl.getFrames().get(5);
        assertEquals(STRIKE, carlFrame5.getFirstScore());
        var carlFrame6 = carl.getFrames().get(6);
        assertEquals(STRIKE, carlFrame6.getFirstScore());
        var carlFrame7 = carl.getFrames().get(7);
        assertEquals(STRIKE, carlFrame7.getFirstScore());
        var carlFrame8 = carl.getFrames().get(8);
        assertEquals(STRIKE, carlFrame8.getFirstScore());
        var carlFrame9 = carl.getFrames().get(9);
        assertEquals(STRIKE, carlFrame9.getFirstScore());
        assertEquals(STRIKE, carlFrame9.getSecondScore());
        assertEquals(STRIKE, carlFrame9.getThirdScore());
    }

    @Test
    public void shouldPlayBowlingWithZeroScore() throws IOException {
        // Given
        var args = "src/test/resources/positive/zero.txt";
        var file = new File(args);
        var bowlingMoves = new ArrayList<BowlingMove>();
        try (var lines = Files.lines(file.toPath())) {
            lines.forEach(line -> {
                var columns = line.split(TAB);
                bowlingMoves.add(new BowlingMove(columns[0], columns[1]));
            });
        }
        var bowling = new Bowling();

        // When
        bowlingGame.play(bowling, bowlingMoves);

        // Then
        var players = bowling.getPlayers();
        assertEquals(1, players.size());

        var carl = players.get(0);
        assertEquals("Carl", carl.getName());
        assertEquals(10, carl.getFrames().size());

        var carlFrame0 = carl.getFrames().get(0);
        assertEquals("0", carlFrame0.getFirstScore());
        var carlFrame1 = carl.getFrames().get(1);
        assertEquals("0", carlFrame1.getFirstScore());
        var carlFrame2 = carl.getFrames().get(2);
        assertEquals("0", carlFrame2.getFirstScore());
        var carlFrame3 = carl.getFrames().get(3);
        assertEquals("0", carlFrame3.getFirstScore());
        var carlFrame4 = carl.getFrames().get(4);
        assertEquals("0", carlFrame4.getFirstScore());
        var carlFrame5 = carl.getFrames().get(5);
        assertEquals("0", carlFrame5.getFirstScore());
        var carlFrame6 = carl.getFrames().get(6);
        assertEquals("0", carlFrame6.getFirstScore());
        var carlFrame7 = carl.getFrames().get(7);
        assertEquals("0", carlFrame7.getFirstScore());
        var carlFrame8 = carl.getFrames().get(8);
        assertEquals("0", carlFrame8.getFirstScore());
        var carlFrame9 = carl.getFrames().get(9);
        assertEquals("0", carlFrame9.getFirstScore());
    }

    @Test
    public void shouldPlayBowlingWithFoulScore() throws IOException {
        // Given
        var args = "src/test/resources/positive/foul.txt";
        var file = new File(args);
        var bowlingMoves = new ArrayList<BowlingMove>();
        try (var lines = Files.lines(file.toPath())) {
            lines.forEach(line -> {
                var columns = line.split(TAB);
                bowlingMoves.add(new BowlingMove(columns[0], columns[1]));
            });
        }
        var bowling = new Bowling();

        // When
        bowlingGame.play(bowling, bowlingMoves);

        // Then
        var players = bowling.getPlayers();
        assertEquals(1, players.size());

        var carl = players.get(0);
        assertEquals("Carl", carl.getName());
        assertEquals(10, carl.getFrames().size());

        var carlFrame0 = carl.getFrames().get(0);
        assertEquals("F", carlFrame0.getFirstScore());
        var carlFrame1 = carl.getFrames().get(1);
        assertEquals("F", carlFrame1.getFirstScore());
        var carlFrame2 = carl.getFrames().get(2);
        assertEquals("F", carlFrame2.getFirstScore());
        var carlFrame3 = carl.getFrames().get(3);
        assertEquals("F", carlFrame3.getFirstScore());
        var carlFrame4 = carl.getFrames().get(4);
        assertEquals("F", carlFrame4.getFirstScore());
        var carlFrame5 = carl.getFrames().get(5);
        assertEquals("F", carlFrame5.getFirstScore());
        var carlFrame6 = carl.getFrames().get(6);
        assertEquals("F", carlFrame6.getFirstScore());
        var carlFrame7 = carl.getFrames().get(7);
        assertEquals("F", carlFrame7.getFirstScore());
        var carlFrame8 = carl.getFrames().get(8);
        assertEquals("F", carlFrame8.getFirstScore());
        var carlFrame9 = carl.getFrames().get(9);
        assertEquals("F", carlFrame9.getFirstScore());
    }

}
