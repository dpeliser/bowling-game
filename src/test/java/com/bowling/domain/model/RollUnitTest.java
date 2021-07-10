package com.bowling.domain.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link Roll} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
public class RollUnitTest {

    @Test
    public void isFirstShouldReturnTrue() {
        assertTrue(Roll.FIRST.isFirst());
    }

    @Test
    public void isFirstShouldReturnFalse() {
        assertFalse(Roll.SECOND.isFirst());
        assertFalse(Roll.THIRD.isFirst());
    }

    @Test
    public void isSecondShouldReturnTrue() {
        assertTrue(Roll.SECOND.isSecond());
    }

    @Test
    public void isSecondShouldReturnFalse() {
        assertFalse(Roll.FIRST.isSecond());
        assertFalse(Roll.THIRD.isSecond());
    }

    @Test
    public void nextShouldReturnSECOND() {
        assertEquals(Roll.SECOND, Roll.FIRST.next());
    }

    @Test
    public void nextShouldReturnTHIRD() {
        assertEquals(Roll.THIRD, Roll.SECOND.next());
    }

    @Test
    public void nextShouldReturnNull() {
        assertNull(Roll.THIRD.next());
    }

}
