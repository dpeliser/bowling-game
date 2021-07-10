package com.bowling.domain.runner;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.bowling.BowlingApplication;
import com.bowling.core.exception.InvalidBowlingMatchException;
import com.bowling.core.exception.InvalidFileException;
import com.bowling.domain.validator.FileValidator;
import com.bowling.infrastructure.service.StdoutPrintService;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.bowling.domain.utils.ErrorMessages.INVALID_BOWLING_MATCH_PLAYER_MUST_HAVE_10_FRAMES;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_IS_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link BowlingRunner} integration test.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = BowlingApplication.class,
    initializers = ConfigFileApplicationContextInitializer.class
)
public class BowlingRunnerIntegrationTest {

    private final Logger log = (Logger) LoggerFactory.getLogger(StdoutPrintService.class);

    @Autowired
    private FileValidator fileValidator;

    @Autowired
    private BowlingRunner runner;

    @Test
    public void shouldPlayBowling() throws Exception {
        // Given
        var args = "src/test/resources/positive/scores.txt";
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        log.addAppender(listAppender);

        // When
        runner.run(args);

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
    public void shouldPlayBowlingWithPerfectScore() throws Exception {
        // Given
        var args = "src/test/resources/positive/perfect.txt";
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        log.addAppender(listAppender);

        // When
        runner.run(args);

        // Then
        var logs = listAppender.list;
        assertEquals(4, logs.size());
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", logs.get(0).getMessage());
        assertEquals("Carl", logs.get(1).getMessage());
        assertEquals("Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX", logs.get(2).getMessage());
        assertEquals("Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300",
            logs.get(3).getMessage());
    }

    @Test
    public void shouldPlayBowlingWithZeroScore() throws Exception {
        // Given
        var args = "src/test/resources/positive/zero.txt";
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        log.addAppender(listAppender);

        // When
        runner.run(args);

        // Then
        var logs = listAppender.list;
        assertEquals(4, logs.size());
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", logs.get(0).getMessage());
        assertEquals("Carl", logs.get(1).getMessage());
        assertEquals("Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0", logs.get(2).getMessage());
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0",
            logs.get(3).getMessage());
    }

    @Test
    public void shouldPlayBowlingWithFoulScore() throws Exception {
        // Given
        var args = "src/test/resources/positive/foul.txt";
        var listAppender = new ListAppender<ILoggingEvent>();
        listAppender.start();
        log.addAppender(listAppender);

        // When
        runner.run(args);

        // Then
        var logs = listAppender.list;
        assertEquals(4, logs.size());
        assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", logs.get(0).getMessage());
        assertEquals("Carl", logs.get(1).getMessage());
        assertEquals("Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF", logs.get(2).getMessage());
        assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0",
            logs.get(3).getMessage());
    }

    @Test
    public void shouldNotPlayBowlingWithEmptyScore() {
        // Given
        var args = "src/test/resources/negative/empty.txt";

        // When and Then
        var exception = assertThrows(InvalidFileException.class, () -> runner.run(args));
        assertEquals(THE_FILE_INFORMED_IS_EMPTY, exception.getMessage());
    }

    @Test
    public void shouldNotPlayBowlingWithExtraScore() {
        // Given
        var args = "src/test/resources/negative/extra-score.txt";

        // When and Then
        var exception = assertThrows(InvalidBowlingMatchException.class, () -> runner.run(args));
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
    public void shouldNotPlayBowlingWithFreeText() {
        // Given
        var args = "src/test/resources/negative/free-text.txt";

        // When and Then
        var exception = assertThrows(InvalidFileException.class, () -> runner.run(args));
        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT, exception.getMessage());
    }

    @Test
    public void shouldNotPlayBowlingWithInvalidScore() {
        // Given
        var args = "src/test/resources/negative/invalid-score.txt";

        // When and Then
        var exception = assertThrows(InvalidFileException.class, () -> runner.run(args));
        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());
    }

    @Test
    public void shouldNotPlayBowlingMissingScore() {
        // Given
        var args = "src/test/resources/negative/missing-score.txt";

        // When and Then
        var exception = assertThrows(InvalidBowlingMatchException.class, () -> runner.run(args));
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
    public void shouldNotPlayBowlingWithNegativeScore() {
        // Given
        var args = "src/test/resources/negative/negative.txt";

        // When and Then
        var exception = assertThrows(InvalidFileException.class, () -> runner.run(args));
        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());
    }

}
