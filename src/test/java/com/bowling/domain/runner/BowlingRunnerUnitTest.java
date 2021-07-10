package com.bowling.domain.runner;

import com.bowling.domain.service.PrintService;
import com.bowling.domain.validator.BowlingValidator;
import com.bowling.domain.validator.FileValidator;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.Validator;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link BowlingRunner} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
public class BowlingRunnerUnitTest {

    @Mock
    private FileValidator fileValidator;

    @Mock
    private Validator validator;

    @Mock
    private BowlingValidator bowlingValidator;

    @Mock
    private BowlingGame bowlingGame;

    @Mock
    private BowlingScore bowlingScore;

    @Mock
    private PrintService printService;

    @InjectMocks
    private BowlingRunner runner;

    @Test
    public void runShouldCallFileValidator() throws Exception {
        // Given
        var args = "src/test/resources/positive/scores.txt";

        doNothing().when(fileValidator).validate(args);
        doNothing().when(bowlingValidator).validate(any());
        doNothing().when(bowlingGame).play(any(), anyList());
        doNothing().when(bowlingScore).calculate(any());
        doNothing().when(printService).print(any());
        when(validator.validate(any())).thenReturn(Collections.emptySet());

        // When
        runner.run(args);

        // Then
        verify(fileValidator).validate(eq(args));
    }

    @Test
    public void runShouldCallValidator() throws Exception {
        // Given
        var args = "src/test/resources/positive/scores.txt";

        doNothing().when(fileValidator).validate(args);
        doNothing().when(bowlingValidator).validate(any());
        doNothing().when(bowlingGame).play(any(), anyList());
        doNothing().when(bowlingScore).calculate(any());
        doNothing().when(printService).print(any());
        when(validator.validate(any())).thenReturn(Collections.emptySet());

        // When
        runner.run(args);

        // Then
        verify(validator, times(35)).validate(any());
    }

    @Test
    public void runShouldCallBowlingGame() throws Exception {
        // Given
        var args = "src/test/resources/positive/scores.txt";

        doNothing().when(fileValidator).validate(args);
        doNothing().when(bowlingValidator).validate(any());
        doNothing().when(bowlingGame).play(any(), anyList());
        doNothing().when(bowlingScore).calculate(any());
        doNothing().when(printService).print(any());
        when(validator.validate(any())).thenReturn(Collections.emptySet());

        // When
        runner.run(args);

        // Then
        verify(bowlingGame).play(any(), anyList());
    }

    @Test
    public void runShouldCallBowlingValidator() throws Exception {
        // Given
        var args = "src/test/resources/positive/scores.txt";

        doNothing().when(fileValidator).validate(args);
        doNothing().when(bowlingValidator).validate(any());
        doNothing().when(bowlingGame).play(any(), anyList());
        doNothing().when(bowlingScore).calculate(any());
        doNothing().when(printService).print(any());
        when(validator.validate(any())).thenReturn(Collections.emptySet());

        // When
        runner.run(args);

        // Then
        verify(bowlingValidator).validate(any());
    }

    @Test
    public void runShouldCallBowlingScore() throws Exception {
        // Given
        var args = "src/test/resources/positive/scores.txt";

        doNothing().when(fileValidator).validate(args);
        doNothing().when(bowlingValidator).validate(any());
        doNothing().when(bowlingGame).play(any(), anyList());
        doNothing().when(bowlingScore).calculate(any());
        doNothing().when(printService).print(any());
        when(validator.validate(any())).thenReturn(Collections.emptySet());

        // When
        runner.run(args);

        // Then
        verify(bowlingScore).calculate(any());
    }

    @Test
    public void runShouldCallPrintService() throws Exception {
        // Given
        var args = "src/test/resources/positive/scores.txt";

        doNothing().when(fileValidator).validate(args);
        doNothing().when(bowlingValidator).validate(any());
        doNothing().when(bowlingGame).play(any(), anyList());
        doNothing().when(bowlingScore).calculate(any());
        doNothing().when(printService).print(any());
        when(validator.validate(any())).thenReturn(Collections.emptySet());

        // When
        runner.run(args);

        // Then
        verify(printService).print(any());
    }

}
