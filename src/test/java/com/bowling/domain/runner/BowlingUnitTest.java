package com.bowling.domain.runner;

import lombok.var;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@link Bowling} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.0
 */
public class BowlingUnitTest {

    @Test
    public void getPlayerShouldReturnANewPlayerIfDoesNotExist() {
        // Give
        var playerName = "New Player";
        var bowling = new Bowling();

        // When
        var player = bowling.getPlayer(playerName);

        // Then
        assertNotNull(player);
        assertNotNull(player.getName());
        assertEquals(playerName, player.getName());
    }

    @Test
    public void getPlayerShouldReturnPlayerIfExists() {
        // Give
        var playerName = "New Player";
        var bowling = new Bowling();

        // When
        var newPlayer = bowling.getPlayer(playerName);
        var player = bowling.getPlayer(playerName);

        // Then
        assertNotNull(newPlayer);
        assertNotNull(player);
        assertEquals(newPlayer, player);
    }

}
