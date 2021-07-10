package com.bowling.domain.model;

import lombok.var;
import org.junit.Test;

import static com.bowling.domain.utils.Constants.SPARE;
import static com.bowling.domain.utils.Constants.STRIKE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link Frame} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
public class FrameUnitTest {

    @Test
    public void finishCurrentRollShouldSetRollToTheNextOne() {
        // Given
        var frame = new Frame(0);

        // When
        frame.finishCurrentRoll();

        // Then
        assertEquals(Roll.SECOND, frame.getRoll());

        // When
        frame.finishCurrentRoll();

        // Then
        assertEquals(Roll.THIRD, frame.getRoll());
    }

    @Test
    public void hasAdditionalRollShouldReturnTrueIfIsLastAndIsStrike() {
        // Given
        var frame = new Frame(10);
        frame.setFirstScore(STRIKE);

        // When
        frame.hasAdditionalRoll();

        // Then
        assertTrue(frame.hasAdditionalRoll());
    }

    @Test
    public void hasAdditionalRollShouldReturnTrueIfIsLastAndIsSpare() {
        // Given
        var frame = new Frame(10);
        frame.setFirstScore("8");
        frame.setSecondScore("2");

        // When
        frame.hasAdditionalRoll();

        // Then
        assertTrue(frame.hasAdditionalRoll());
    }

    @Test
    public void hasAdditionalRollShouldReturnFalseIfIsNotLastAndIsStrike() {
        // Given
        var frame = new Frame(9);
        frame.setFirstScore(STRIKE);

        // When
        frame.hasAdditionalRoll();

        // Then
        assertFalse(frame.hasAdditionalRoll());
    }

    @Test
    public void hasAdditionalRollShouldReturnFalseIfIsNotLastAndIsSpare() {
        // Given
        var frame = new Frame(9);
        frame.setFirstScore("8");
        frame.setSecondScore("2");

        // When
        frame.hasAdditionalRoll();

        // Then
        assertFalse(frame.hasAdditionalRoll());
    }

    @Test
    public void hasAdditionalRollShouldReturnFalseIfIsLastAndIsNotStrikeAndIsNotSpare() {
        // Given
        var frame = new Frame(10);
        frame.setFirstScore("8");
        frame.setSecondScore("1");

        // When
        frame.hasAdditionalRoll();

        // Then
        assertFalse(frame.hasAdditionalRoll());
    }

    @Test
    public void isLastShouldReturnTrueIfIndexIs10() {
        // Given
        var frame = new Frame(10);

        // When and Then
        assertTrue(frame.isLast());
    }

    @Test
    public void isLastShouldReturnFalseIfIndexIsNot10() {
        // Given
        var frame = new Frame(9);

        // When and Then
        assertFalse(frame.isLast());
    }

    @Test
    public void isStrikeShouldReturnTrueIfFirstScoreIsStrike() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("10");

        // When and Then
        assertTrue(frame.isStrike());
    }

    @Test
    public void isStrikeShouldReturnFalseIfFirstScoreIsNotStrike() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("9");

        // When and Then
        assertFalse(frame.isStrike());
    }

    @Test
    public void isSpareShouldReturnTrueIfFirstAndSecondScoresAreSpare() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("5");
        frame.setSecondScore("5");

        // When and Then
        assertTrue(frame.isSpare());
    }

    @Test
    public void isSpareShouldReturnFalseIfFirstAndSecondScoresAreNotSpare() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("5");
        frame.setSecondScore("4");

        // When and Then
        assertFalse(frame.isSpare());
    }

    @Test
    public void getFirstScoreShouldReturnSTRIKEIfFirstStrikeIs10() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("10");

        // When
        var score = frame.getFirstScore();

        // Then
        assertEquals(STRIKE, score);
    }

    @Test
    public void getFirstScoreShouldReturnNumberAsStringIfFirstStrikeIsNot10() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("9");

        // When
        var score = frame.getFirstScore();

        // Then
        assertEquals("9", score);
    }

    @Test
    public void getFirstScoreAsIntShouldReturnFirstScoreAsInt() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("10");

        // When
        var score = frame.getFirstScoreAsInt();

        // Then
        assertEquals(10, score);
    }

    @Test
    public void getSecondScoreShouldReturnSPAREIfFirstAndSecondStrikesAre10() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("4");
        frame.setSecondScore("6");

        // When
        var score = frame.getSecondScore();

        // Then
        assertEquals(SPARE, score);
    }

    @Test
    public void getSecondScoreShouldReturnSTRIKEIfSecondStrikeIs10() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("4");
        frame.setSecondScore("10");

        // When
        var score = frame.getSecondScore();

        // Then
        assertEquals(STRIKE, score);
    }

    @Test
    public void getSecondScoreShouldReturnNumberAsStringIfSecondStrikeIsNot10() {
        // Given
        var frame = new Frame(0);
        frame.setFirstScore("4");
        frame.setSecondScore("1");

        // When
        var score = frame.getSecondScore();

        // Then
        assertEquals("1", score);
    }

    @Test
    public void getSecondScoreAsIntShouldReturnSecondScoreAsInt() {
        // Given
        var frame = new Frame(0);
        frame.setSecondScore("10");

        // When
        var score = frame.getSecondScoreAsInt();

        // Then
        assertEquals(10, score);
    }

    @Test
    public void getThirdScoreShouldReturnSTRIKEIfThirdStrikeIs10() {
        // Given
        var frame = new Frame(0);
        frame.setThirdScore("10");

        // When
        var score = frame.getThirdScore();

        // Then
        assertEquals(STRIKE, score);
    }

    @Test
    public void getThirdScoreShouldReturnNumberAsStringIfThirdStrikeIsNot10() {
        // Given
        var frame = new Frame(0);
        frame.setThirdScore("3");

        // When
        var score = frame.getThirdScore();

        // Then
        assertEquals("3", score);
    }

    @Test
    public void getThirdScoreAsIntShouldReturnThirdScoreAsInt() {
        // Given
        var frame = new Frame(0);
        frame.setThirdScore("10");

        // When
        var score = frame.getThirdScoreAsInt();

        // Then
        assertEquals(10, score);
    }

}
