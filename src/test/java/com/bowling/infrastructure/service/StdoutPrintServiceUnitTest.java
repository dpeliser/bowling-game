package com.bowling.infrastructure.service;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.bowling.domain.model.BowlingMove;
import com.bowling.domain.runner.Bowling;
import com.bowling.game.BowlingGameImpl;
import com.bowling.game.BowlingScoreImpl;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link StdoutPrintService} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
public class StdoutPrintServiceUnitTest {

    private final Logger log = (Logger) LoggerFactory.getLogger(StdoutPrintService.class);

    @InjectMocks
    private BowlingGameImpl bowlingGame;

    @InjectMocks
    private BowlingScoreImpl bowlingScore;

    @InjectMocks
    private StdoutPrintService service;

    @Test
    public void shouldPrintScore() {
        // Given
        var bowlingMoves = Arrays.asList(
            new BowlingMove("Michael", "0"),
            new BowlingMove("Michael", "0"),
            new BowlingMove("Michael", "10"),
            new BowlingMove("Michael", "0"),
            new BowlingMove("Michael", "0"),
            new BowlingMove("Michael", "10"),
            new BowlingMove("Michael", "10"),
            new BowlingMove("Michael", "10"),
            new BowlingMove("Michael", "0"),
            new BowlingMove("Michael", "0"),
            new BowlingMove("Michael", "0"),
            new BowlingMove("Michael", "0"),
            new BowlingMove("Michael", "10"),
            new BowlingMove("Michael", "8"),
            new BowlingMove("Michael", "2"),
            new BowlingMove("Michael", "10")
        );
        var bowling = new Bowling();
        bowlingGame.play(bowling, bowlingMoves);
        bowlingScore.calculate(bowling);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        log.addAppender(listAppender);

        // When
        service.print(bowling);

        // Then
        var logs = listAppender.list;
        assertEquals(4, logs.size());
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", logs.get(0).getMessage());
        assertEquals("Michael", logs.get(1).getMessage());
        assertEquals("Pinfalls\t0\t0\t\tX\t0\t0\t\tX\t\tX\t\tX\t0\t0\t0\t0\t\tX\t8\t/\tX", logs.get(2).getMessage());
        assertEquals("Score\t\t0\t\t10\t\t10\t\t40\t\t60\t\t70\t\t70\t\t70\t\t90\t\t110",
            logs.get(3).getMessage());
    }

    @Test
    public void shouldPrintScores() {
        // Given
        var bowlingMoves = Arrays.asList(
            new BowlingMove("Jeff", "10"),
            new BowlingMove("John", "3"),
            new BowlingMove("John", "7"),
            new BowlingMove("Jeff", "7"),
            new BowlingMove("Jeff", "3"),
            new BowlingMove("John", "6"),
            new BowlingMove("John", "3"),
            new BowlingMove("Jeff", "9"),
            new BowlingMove("Jeff", "0"),
            new BowlingMove("John", "10"),
            new BowlingMove("Jeff", "10"),
            new BowlingMove("John", "8"),
            new BowlingMove("John", "1"),
            new BowlingMove("Jeff", "0"),
            new BowlingMove("Jeff", "8"),
            new BowlingMove("John", "10"),
            new BowlingMove("Jeff", "8"),
            new BowlingMove("Jeff", "2"),
            new BowlingMove("John", "10"),
            new BowlingMove("Jeff", "F"),
            new BowlingMove("Jeff", "6"),
            new BowlingMove("John", "9"),
            new BowlingMove("John", "0"),
            new BowlingMove("Jeff", "10"),
            new BowlingMove("John", "7"),
            new BowlingMove("John", "3"),
            new BowlingMove("Jeff", "10"),
            new BowlingMove("John", "4"),
            new BowlingMove("John", "4"),
            new BowlingMove("Jeff", "10"),
            new BowlingMove("Jeff", "8"),
            new BowlingMove("Jeff", "1"),
            new BowlingMove("John", "10"),
            new BowlingMove("John", "9"),
            new BowlingMove("John", "0")
        );
        var bowling = new Bowling();
        bowlingGame.play(bowling, bowlingMoves);
        bowlingScore.calculate(bowling);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        log.addAppender(listAppender);

        // When
        service.print(bowling);

        // Then
        var logs = listAppender.list;
        assertEquals(7, logs.size());
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", logs.get(0).getMessage());
        assertEquals("Jeff", logs.get(1).getMessage());
        assertEquals("Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1", logs.get(2).getMessage());
        assertEquals("Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167", logs.get(3).getMessage());
        assertEquals("John", logs.get(4).getMessage());
        assertEquals("Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0", logs.get(5).getMessage());
        assertEquals("Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151",
            logs.get(6).getMessage());
    }

    @Test
    public void shouldPrintMinimumScore() {
        // Given
        var bowlingMoves = Arrays.asList(
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0"),
            new BowlingMove("Diego", "0")
        );
        var bowling = new Bowling();
        bowlingGame.play(bowling, bowlingMoves);
        bowlingScore.calculate(bowling);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        log.addAppender(listAppender);

        // When
        service.print(bowling);

        // Then
        var logs = listAppender.list;
        assertEquals(4, logs.size());
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", logs.get(0).getMessage());
        assertEquals("Diego", logs.get(1).getMessage());
        assertEquals("Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0", logs.get(2).getMessage());
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0",
            logs.get(3).getMessage());
    }

    @Test
    public void shouldPrintPerfectScore() {
        // Given
        var bowlingMoves = Arrays.asList(
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10"),
            new BowlingMove("Carl", "10")
        );
        var bowling = new Bowling();
        bowlingGame.play(bowling, bowlingMoves);
        bowlingScore.calculate(bowling);
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        log.addAppender(listAppender);

        // When
        service.print(bowling);

        // Then
        var logs = listAppender.list;
        assertEquals(4, logs.size());
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", logs.get(0).getMessage());
        assertEquals("Carl", logs.get(1).getMessage());
        assertEquals("Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX", logs.get(2).getMessage());
        assertEquals("Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300",
            logs.get(3).getMessage());
    }

}
