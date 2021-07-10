package com.bowling.game;

import com.bowling.domain.model.Frame;
import com.bowling.domain.runner.Bowling;
import com.bowling.domain.runner.BowlingScore;
import lombok.var;
import org.springframework.stereotype.Component;

/**
 * Calculates the score of a {@link Bowling} game.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.1
 */
@Component
public class BowlingScoreImpl implements BowlingScore {

    /**
     * Calculates the score of a {@link Bowling} game.
     *
     * @param bowling to calculate the score
     */
    @Override
    public void calculate(final Bowling bowling) {
        bowling.getPlayers().forEach(player -> {
            var frames = player.getFrames();
            var size = frames.size();
            var totalScore = 0;
            for (int i = 0; i < size; i++) {
                var frame = frames.get(i);
                if (frame.isLast()) {
                    totalScore += getLastScore(frame);
                } else if (frame.isStrike()) {
                    var nextFrame = frames.get(i + 1);
                    var secondNextFrameIndex = i + 2;
                    var secondNextFrame = secondNextFrameIndex < size ? frames.get(secondNextFrameIndex) : null;
                    totalScore += getStrikeScore(frame, nextFrame, secondNextFrame);
                } else if (frame.isSpare()) {
                    var nextFrame = frames.get(i + 1);
                    totalScore += getSpareScore(frame, nextFrame);
                } else {
                    totalScore += getScore(frame);
                }
                frame.setTotalScore(totalScore);
            }
        });
    }

    /**
     * Calculates the last score of the frame.
     *
     * @param frame to get the last score
     * @return The last score of the frame.
     */
    private Integer getLastScore(final Frame frame) {
        var totalScore = frame.getFirstScoreAsInt() + frame.getSecondScoreAsInt();
        if (frame.isStrike() || frame.isSpare()) {
            totalScore += frame.getThirdScoreAsInt();
        }
        return totalScore;
    }

    /**
     * Calculates the score of the frame that has a strike.
     *
     * @param frame           that has a strike to get the score
     * @param nextFrame       to calculate the strike score
     * @param secondNextFrame to calculate the strike score
     * @return The total score of the frame that has a strike.
     */
    private Integer getStrikeScore(final Frame frame, final Frame nextFrame, final Frame secondNextFrame) {
        var totalScore = frame.getFirstScoreAsInt() + nextFrame.getFirstScoreAsInt();
        if (nextFrame.isLast()) {
            totalScore += nextFrame.getSecondScoreAsInt();
        } else {
            totalScore += nextFrame.isStrike() ? secondNextFrame.getFirstScoreAsInt() : nextFrame.getSecondScoreAsInt();
        }
        return totalScore;
    }

    /**
     * Calculates the score of the frame that has a spare.
     *
     * @param frame     that has a spare to get the score
     * @param nextFrame to calculate the spare score
     * @return The total score of the frame that has a spare.
     */
    private Integer getSpareScore(final Frame frame, final Frame nextFrame) {
        return frame.getFirstScoreAsInt() + frame.getSecondScoreAsInt() + nextFrame.getFirstScoreAsInt();
    }

    /**
     * Calculates the score of the frame that has neither strike nor spare.
     *
     * @param frame to get the score
     * @return The total score of the frame.
     */
    private Integer getScore(final Frame frame) {
        return frame.getFirstScoreAsInt() + frame.getSecondScoreAsInt();
    }

}
