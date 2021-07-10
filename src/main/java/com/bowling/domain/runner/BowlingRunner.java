package com.bowling.domain.runner;

import com.bowling.domain.model.BowlingMove;
import com.bowling.domain.service.PrintService;
import com.bowling.domain.validator.BowlingValidator;
import com.bowling.domain.validator.FileValidator;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import static com.bowling.domain.utils.Constants.FOUL;
import static com.bowling.domain.utils.Constants.STRIKE;
import static com.bowling.domain.utils.Constants.TAB;

/**
 * Runner for a bowling game.
 *
 * @author Diego Peliser
 * @version 1.0.1
 * @since 1.0.0
 */
@Slf4j
@Component
public class BowlingRunner implements CommandLineRunner {

    @Autowired
    private FileValidator fileValidator;

    @Autowired
    private Validator validator;

    @Autowired
    private BowlingValidator bowlingValidator;

    @Autowired
    private BowlingGame bowlingGame;

    @Autowired
    private BowlingScore bowlingScore;

    @Autowired
    private PrintService printService;

    @Override
    public void run(final String... args) throws Exception {
        fileValidator.validate(args);

        var fileName = args[0];
        var file = new File(fileName);

        var bowlingMoves = new ArrayList<BowlingMove>();
        try (var lines = Files.lines(file.toPath())) {
            lines.forEach(line -> {
                fileValidator.validateLine(line);
                bowlingMoves.add(buildBowlingMove(line));
            });
        }

        var bowling = new Bowling();

        bowlingGame.play(bowling, bowlingMoves);

        bowlingValidator.validate(bowling);

        bowlingScore.calculate(bowling);

        printService.print(bowling);
    }

    /**
     * Build a {@link BowlingMove} from the given move.
     *
     * @param move to build a {@link BowlingMove}
     * @return A new {@link BowlingMove}.
     */
    private BowlingMove buildBowlingMove(final String move) {
        var columns = move.split(TAB);
        var playerName = columns[0];
        var score = parseScore(columns[1]);
        var bowlingMove = new BowlingMove(playerName, score);
        var errors = validator.validate(bowlingMove);
        if (!errors.isEmpty()) {
            throw new ConstraintViolationException(errors);
        }
        return bowlingMove;
    }

    /**
     * Parse the score checking if it is a valid number or a foul.
     *
     * @param score to parse
     * @return Score parsed if is a valid number or a foul, null otherwise.
     */
    private String parseScore(final String score) {
        if (score != null) {
            if (score.equals(FOUL)) {
                return score;
            }
            try {
                // Convert to int (and then back to String) to make sure that is a valid int.
                var scoreAsInt = Integer.parseInt(score);
                return scoreAsInt == 10 ? STRIKE : score;
            } catch (final NumberFormatException e) {
                log.debug("Ignoring error to convert score to int", e);
                return null;
            }
        }
        return null;
    }

}
