package com.bowling.domain.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.bowling.domain.utils.Constants.FOUL;
import static com.bowling.domain.utils.Constants.SPARE;
import static com.bowling.domain.utils.Constants.STRIKE;

/**
 * Represents a bowling frame.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@RequiredArgsConstructor
public class Frame {

    @NonNull
    private Integer index;

    private String firstScore;

    private String secondScore;

    private String thirdScore;

    private Integer totalScore;

    private Roll roll = Roll.FIRST;

    /**
     * Finished the current roll moving to the next one.
     */
    public void finishCurrentRoll() {
        this.roll = this.roll.next();
    }

    /**
     * Check if this is the last roll and is a strike or a spare to have additional roll.
     *
     * @return True if this frame has additional roll, false otherwise.
     */
    public boolean hasAdditionalRoll() {
        return isLast() && (isStrike() || isSpare());
    }

    /**
     * Check if this is the last frame.
     *
     * @return True if this is the last index, false otherwise.
     */
    public boolean isLast() {
        return this.index == 10;
    }

    /**
     * Check if the first score is a strike (10 points).
     *
     * @return True if is a strike, false otherwise.
     */
    public boolean isStrike() {
        return getFirstScoreAsInt() == 10;
    }

    /**
     * Check if the first and second scores are a spare (10 points).
     *
     * @return True if is a spare, false otherwise.
     */
    public boolean isSpare() {
        return !isStrike() && getFirstScoreAsInt() + getSecondScoreAsInt() == 10;
    }

    /**
     * Getter for firstScore.
     *
     * @return First score.
     */
    public String getFirstScore() {
        return this.firstScore.equals("10") ? STRIKE : this.firstScore;
    }

    /**
     * Getter for firstScore as int.
     *
     * @return First score as int.
     */
    public int getFirstScoreAsInt() {
        return scoreAsInt(this.firstScore);
    }

    /**
     * Getter for secondScore.
     *
     * @return Second score.
     */
    public String getSecondScore() {
        if (this.isSpare()) {
            return SPARE;
        }
        return this.secondScore.equals("10") ? STRIKE : this.secondScore;
    }

    /**
     * Getter for secondScore as int.
     *
     * @return Second score as int.
     */
    public int getSecondScoreAsInt() {
        return scoreAsInt(this.secondScore);
    }

    /**
     * Getter for firstScore.
     *
     * @return First score.
     */
    public String getThirdScore() {
        return this.thirdScore.equals("10") ? STRIKE : this.thirdScore;
    }

    /**
     * Getter for thirdScore as int.
     *
     * @return Third score as int.
     */
    public int getThirdScoreAsInt() {
        return scoreAsInt(this.thirdScore);
    }

    /**
     * Converts the score to a int.
     *
     * @param score to convert
     * @return The score as int.
     */
    private int scoreAsInt(final String score) {
        if (score.equals(STRIKE)) {
            return 10;
        }
        return score.equals(FOUL) ? 0 : Integer.parseInt(score);
    }

}
