package com.bowling.domain.validator;

import com.bowling.core.exception.InvalidFileException;
import com.bowling.core.exception.InvalidParameterException;
import lombok.var;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.regex.Pattern;

import static com.bowling.domain.utils.Constants.TAB;
import static com.bowling.domain.utils.ErrorMessages.A_FILE_WITH_THE_NAME_INFORMED_DOES_NOT_EXISTS;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_PLAYER_NAME;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_IS_EMPTY;
import static com.bowling.domain.utils.ErrorMessages.YOU_NEED_TO_PASS_A_FILE_NAME_AS_PARAMETER;

/**
 * Validator for the input {@link File} for a bowling match.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class FileValidator {

    private final Pattern playerNamePattern = Pattern.compile("[a-zA-Z]+");
    private final Pattern scorePattern = Pattern.compile("^([0-9]|[0-1][0])|F");

    /**
     * Validates if the given args is a valid file name.
     *
     * @param args to validate
     */
    public void validate(final String... args) {
        if (args == null || args.length == 0) {
            throw new InvalidParameterException(YOU_NEED_TO_PASS_A_FILE_NAME_AS_PARAMETER);
        }

        var fileName = args[0];
        var file = new File(fileName);

        if (!file.exists()) {
            throw new InvalidFileException(A_FILE_WITH_THE_NAME_INFORMED_DOES_NOT_EXISTS);
        }

        if (file.length() == 0) {
            throw new InvalidFileException(THE_FILE_INFORMED_IS_EMPTY);
        }
    }

    /**
     * Validates if the given line has a valid format.
     *
     * @param line to validate
     */
    public void validateLine(final String line) {
        var columns = line.split(TAB);
        if (columns.length != 2) {
            throw new InvalidFileException(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT);
        }
        if (!playerNamePattern.matcher(columns[0]).matches()) {
            throw new InvalidFileException(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_PLAYER_NAME);
        }
        if (!scorePattern.matcher(columns[1]).matches()) {
            throw new InvalidFileException(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE);
        }
    }

}
