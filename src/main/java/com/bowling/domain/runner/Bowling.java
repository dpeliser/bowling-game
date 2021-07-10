package com.bowling.domain.runner;

import com.bowling.domain.model.Player;
import lombok.Getter;
import lombok.var;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bowling game.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.0
 */
@Getter
public class Bowling {

    private final List<Player> players = new ArrayList<>();

    /**
     * Get a player of the bowling match by name.
     *
     * @param playerName to get the player
     * @return A player of the bowling match.
     */
    public Player getPlayer(final String playerName) {
        var player = this.players.stream()
            .filter(currentPlayer -> currentPlayer.getName().equals(playerName))
            .findFirst()
            .orElse(null);
        if (player == null) {
            player = new Player(playerName);
            this.players.add(player);
        }
        return player;
    }

}
