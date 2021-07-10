package com.bowling.game;

import com.bowling.domain.model.BowlingMove;
import com.bowling.domain.model.Player;
import com.bowling.domain.model.Roll;
import com.bowling.domain.runner.Bowling;
import com.bowling.domain.runner.BowlingGame;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Plays a {@link Bowling} game.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.1
 */
@Component
public class BowlingGameImpl implements BowlingGame {

    /**
     * Play a bowling match.
     *
     * @param bowlingMoves The bowling rolls.
     */
    public void play(final Bowling bowling, final List<BowlingMove> bowlingMoves) {
        bowlingMoves.forEach(bowlingMove -> {
            var player = bowling.getPlayer(bowlingMove.getPlayerName());
            var currentFrame = player.getCurrentFrame();
            var roll = currentFrame.getRoll();

            currentFrame.finishCurrentRoll();

            if (currentFrame.isLast()) {
                addLastMove(bowlingMove, player, roll);
            } else {
                addNotLastMove(bowlingMove, player, roll);
            }
        });
    }

    /**
     * Add the not last {@link BowlingMove} of a {@link Player} to the match.
     *
     * @param bowlingMove to add
     * @param player      of the bowling move
     * @param roll        of the current frame
     */
    private void addNotLastMove(final BowlingMove bowlingMove, final Player player, final Roll roll) {
        var currentFrame = player.getCurrentFrame();
        var score = bowlingMove.getScore();
        if (roll.isFirst()) {
            currentFrame.setFirstScore(score);
            if (currentFrame.isStrike()) {
                player.finishCurrentFrame();
            }
        } else {
            currentFrame.setSecondScore(score);
            player.finishCurrentFrame();
        }
    }

    /**
     * Add the last {@link BowlingMove} of a {@link Player} to the match.
     *
     * @param bowlingMove to add
     * @param player      of the bowling move
     * @param roll        of the current frame
     */
    private void addLastMove(final BowlingMove bowlingMove, final Player player, final Roll roll) {
        var currentFrame = player.getCurrentFrame();
        var score = bowlingMove.getScore();
        if (roll.isFirst()) {
            currentFrame.setFirstScore(score);
        } else if (roll.isSecond()) {
            currentFrame.setSecondScore(score);
            if (!currentFrame.hasAdditionalRoll()) {
                player.finishCurrentFrame();
            }
        } else {
            currentFrame.setThirdScore(score);
            player.finishCurrentFrame();
        }
    }

}
