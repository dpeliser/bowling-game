package com.bowling.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Represents a move of a player in a Bowling match.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public class BowlingMove {

    @NotEmpty
    private final String playerName;

    @NotNull
    private final String score;

}
