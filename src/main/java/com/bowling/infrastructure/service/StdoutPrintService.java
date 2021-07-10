package com.bowling.infrastructure.service;

import com.bowling.domain.model.Frame;
import com.bowling.domain.runner.Bowling;
import com.bowling.domain.service.PrintService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bowling.domain.utils.Constants.SPARE;
import static com.bowling.domain.utils.Constants.STRIKE;
import static com.bowling.domain.utils.Constants.TAB;

/**
 * Print service of {@link Bowling} to stdout.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class StdoutPrintService implements PrintService {

    /**
     * Prints the result of a {@link Bowling}.
     *
     * @param bowling to print the result.
     */
    @Override
    public void print(Bowling bowling) {
        log.info("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        bowling.getPlayers().forEach(player -> {
            log.info(player.getName());

            var frames = player.getFrames();

            printPinfalls(frames);
            printScore(frames);
        });
    }

    /**
     * Prints the Pinfalls of the frames.
     *
     * @param frames to print the Pinfalls.
     */
    private void printPinfalls(final List<Frame> frames) {
        var pinfalls = new StringBuilder("Pinfalls").append(TAB);
        frames.forEach(frame -> {
            if (frame.isLast()) {
                printLastPinfall(pinfalls, frame);
            } else if (frame.isStrike()) {
                pinfalls.append(TAB);
                pinfalls.append(STRIKE);
            } else if (frame.isSpare()) {
                pinfalls.append(frame.getFirstScore());
                pinfalls.append(TAB);
                pinfalls.append(SPARE);
            } else {
                pinfalls.append(frame.getFirstScore());
                pinfalls.append(TAB);
                pinfalls.append(frame.getSecondScore());
            }
            if (!frame.isLast()) {
                pinfalls.append(TAB);
            }
        });
        log.info(pinfalls.toString());
    }

    /**
     * Prints the last Pinfall of the frame.
     *
     * @param pinfalls to add the values from the frame
     * @param frame    with the last Pinfall
     */
    private void printLastPinfall(final StringBuilder pinfalls, final Frame frame) {
        pinfalls.append(frame.getFirstScore());
        pinfalls.append(TAB);
        pinfalls.append(frame.getSecondScore());
        if (frame.isStrike() || frame.isSpare()) {
            pinfalls.append(TAB);
            pinfalls.append(frame.getThirdScore());
        }
    }

    /**
     * Prints the score of the frames.
     *
     * @param frames to print the score
     */
    private void printScore(final List<Frame> frames) {
        var score = new StringBuilder("Score").append(TAB).append(TAB);
        frames.forEach(frame -> {
            score.append(frame.getTotalScore());
            if (!frame.isLast()) {
                score.append(TAB);
                score.append(TAB);
            }
        });
        log.info(score.toString());
    }

}
