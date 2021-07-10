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
import static com.bowling.domain.utils.Constants.TAB;
import static org.junit.Assert.assertEquals;

/**
 * {@link BowlingScoreImpl} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class BowlingScoreImplUnitTest {

    @InjectMocks
    private BowlingGameImpl bowlingGame;

    @InjectMocks
    private BowlingScoreImpl bowlingScore;

    @Test
    public void shouldCalculateScores() throws IOException {
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

        // When
        bowlingScore.calculate(bowling);

        // Then
        var players = bowling.getPlayers();
        assertEquals(2, players.size());

        var jeff = players.get(0);
        assertEquals("Jeff", jeff.getName());
        assertEquals(10, jeff.getFrames().size());

        var jeffFrame0 = jeff.getFrames().get(0);
        assertEquals(20, jeffFrame0.getTotalScore().longValue());
        var jeffFrame1 = jeff.getFrames().get(1);
        assertEquals(39, jeffFrame1.getTotalScore().longValue());
        var jeffFrame2 = jeff.getFrames().get(2);
        assertEquals(48, jeffFrame2.getTotalScore().longValue());
        var jeffFrame3 = jeff.getFrames().get(3);
        ;
        assertEquals(66, jeffFrame3.getTotalScore().longValue());
        var jeffFrame4 = jeff.getFrames().get(4);
        assertEquals(74, jeffFrame4.getTotalScore().longValue());
        var jeffFrame5 = jeff.getFrames().get(5);
        assertEquals(84, jeffFrame5.getTotalScore().longValue());
        var jeffFrame6 = jeff.getFrames().get(6);
        assertEquals(FOUL, jeffFrame6.getFirstScore());
        assertEquals(90, jeffFrame6.getTotalScore().longValue());
        var jeffFrame7 = jeff.getFrames().get(7);
        assertEquals(120, jeffFrame7.getTotalScore().longValue());
        var jeffFrame8 = jeff.getFrames().get(8);
        assertEquals(148, jeffFrame8.getTotalScore().longValue());
        var jeffFrame9 = jeff.getFrames().get(9);
        assertEquals(167, jeffFrame9.getTotalScore().longValue());

        var john = players.get(1);
        assertEquals("John", players.get(1).getName());
        assertEquals(10, john.getFrames().size());

        var johnFrame0 = john.getFrames().get(0);
        assertEquals(16, johnFrame0.getTotalScore().longValue());
        var johnFrame1 = john.getFrames().get(1);
        assertEquals(25, johnFrame1.getTotalScore().longValue());
        var johnFrame2 = john.getFrames().get(2);
        assertEquals(44, johnFrame2.getTotalScore().longValue());
        var johnFrame3 = john.getFrames().get(3);
        assertEquals(53, johnFrame3.getTotalScore().longValue());
        var johnFrame4 = john.getFrames().get(4);
        assertEquals(82, johnFrame4.getTotalScore().longValue());
        var johnFrame5 = john.getFrames().get(5);
        assertEquals(101, johnFrame5.getTotalScore().longValue());
        var johnFrame6 = john.getFrames().get(6);
        assertEquals(110, johnFrame6.getTotalScore().longValue());
        var johnFrame7 = john.getFrames().get(7);
        assertEquals(124, johnFrame7.getTotalScore().longValue());
        var johnFrame8 = john.getFrames().get(8);
        assertEquals(132, johnFrame8.getTotalScore().longValue());
        var johnFrame9 = john.getFrames().get(9);
        assertEquals(151, johnFrame9.getTotalScore().longValue());
    }

    @Test
    public void shouldCalculatePerfectScore() throws IOException {
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
        bowlingGame.play(bowling, bowlingMoves);

        // When
        bowlingScore.calculate(bowling);

        // Then
        var players = bowling.getPlayers();
        assertEquals(1, players.size());

        var carl = players.get(0);
        assertEquals("Carl", carl.getName());
        assertEquals(10, carl.getFrames().size());

        var carlFrame0 = carl.getFrames().get(0);
        assertEquals(30, carlFrame0.getTotalScore().longValue());
        var carlFrame1 = carl.getFrames().get(1);
        assertEquals(60, carlFrame1.getTotalScore().longValue());
        var carlFrame2 = carl.getFrames().get(2);
        assertEquals(90, carlFrame2.getTotalScore().longValue());
        var carlFrame3 = carl.getFrames().get(3);
        assertEquals(120, carlFrame3.getTotalScore().longValue());
        var carlFrame4 = carl.getFrames().get(4);
        assertEquals(150, carlFrame4.getTotalScore().longValue());
        var carlFrame5 = carl.getFrames().get(5);
        assertEquals(180, carlFrame5.getTotalScore().longValue());
        var carlFrame6 = carl.getFrames().get(6);
        assertEquals(210, carlFrame6.getTotalScore().longValue());
        var carlFrame7 = carl.getFrames().get(7);
        assertEquals(240, carlFrame7.getTotalScore().longValue());
        var carlFrame8 = carl.getFrames().get(8);
        assertEquals(270, carlFrame8.getTotalScore().longValue());
        var carlFrame9 = carl.getFrames().get(9);
        assertEquals(300, carlFrame9.getTotalScore().longValue());
    }

    @Test
    public void shouldCalculateZeroScore() throws IOException {
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
        bowlingGame.play(bowling, bowlingMoves);

        // When
        bowlingScore.calculate(bowling);

        // Then
        var players = bowling.getPlayers();
        assertEquals(1, players.size());

        var carl = players.get(0);
        assertEquals("Carl", carl.getName());
        assertEquals(10, carl.getFrames().size());

        var carlFrame0 = carl.getFrames().get(0);
        assertEquals(0, carlFrame0.getTotalScore().longValue());
        var carlFrame1 = carl.getFrames().get(1);
        assertEquals(0, carlFrame1.getTotalScore().longValue());
        var carlFrame2 = carl.getFrames().get(2);
        assertEquals(0, carlFrame2.getTotalScore().longValue());
        var carlFrame3 = carl.getFrames().get(3);
        assertEquals(0, carlFrame3.getTotalScore().longValue());
        var carlFrame4 = carl.getFrames().get(4);
        assertEquals(0, carlFrame4.getTotalScore().longValue());
        var carlFrame5 = carl.getFrames().get(5);
        assertEquals(0, carlFrame5.getTotalScore().longValue());
        var carlFrame6 = carl.getFrames().get(6);
        assertEquals(0, carlFrame6.getTotalScore().longValue());
        var carlFrame7 = carl.getFrames().get(7);
        assertEquals(0, carlFrame7.getTotalScore().longValue());
        var carlFrame8 = carl.getFrames().get(8);
        assertEquals(0, carlFrame8.getTotalScore().longValue());
        var carlFrame9 = carl.getFrames().get(9);
        assertEquals(0, carlFrame9.getTotalScore().longValue());
    }

    @Test
    public void shouldCalculateFoulScore() throws IOException {
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
        bowlingGame.play(bowling, bowlingMoves);

        // When
        bowlingScore.calculate(bowling);

        // Then
        var players = bowling.getPlayers();
        assertEquals(1, players.size());

        var carl = players.get(0);
        assertEquals("Carl", carl.getName());
        assertEquals(10, carl.getFrames().size());

        var carlFrame0 = carl.getFrames().get(0);
        assertEquals(0, carlFrame0.getTotalScore().longValue());
        var carlFrame1 = carl.getFrames().get(1);
        assertEquals(0, carlFrame1.getTotalScore().longValue());
        var carlFrame2 = carl.getFrames().get(2);
        assertEquals(0, carlFrame2.getTotalScore().longValue());
        var carlFrame3 = carl.getFrames().get(3);
        assertEquals(0, carlFrame3.getTotalScore().longValue());
        var carlFrame4 = carl.getFrames().get(4);
        assertEquals(0, carlFrame4.getTotalScore().longValue());
        var carlFrame5 = carl.getFrames().get(5);
        assertEquals(0, carlFrame5.getTotalScore().longValue());
        var carlFrame6 = carl.getFrames().get(6);
        assertEquals(0, carlFrame6.getTotalScore().longValue());
        var carlFrame7 = carl.getFrames().get(7);
        assertEquals(0, carlFrame7.getTotalScore().longValue());
        var carlFrame8 = carl.getFrames().get(8);
        assertEquals(0, carlFrame8.getTotalScore().longValue());
        var carlFrame9 = carl.getFrames().get(9);
        assertEquals(0, carlFrame9.getTotalScore().longValue());
    }

}
