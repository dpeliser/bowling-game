package com.bowling.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Error messages used in the project.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessages {

    public static final String A_FILE_WITH_THE_NAME_INFORMED_DOES_NOT_EXISTS = "A file with the name informed does not exist!";
    public static final String INVALID_BOWLING_MATCH_PLAYER_MUST_HAVE_10_FRAMES =
        "Invalid Bowling match! A player must have 10 frames. Player %s has %d frames!";
    public static final String INVALID_BOWLING_MATCH_WITH_NO_PLAYERS = "Invalid Bowling match with no players!";
    public static final String THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT = "The file informed has a line with an invalid format!";
    public static final String THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_PLAYER_NAME =
        "The file informed has a line with an invalid player name!";
    public static final String THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE =
        "The file informed has a line with an invalid score!";
    public static final String THE_FILE_INFORMED_IS_EMPTY = "The file informed is empty!";
    public static final String YOU_NEED_TO_PASS_A_FILE_NAME_AS_PARAMETER = "You need to pass a file name as parameter!";

}
