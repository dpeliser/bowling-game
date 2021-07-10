package com.bowling.domain.validator;

import com.bowling.core.exception.InvalidFileException;
import com.bowling.core.exception.InvalidParameterException;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.bowling.domain.utils.ErrorMessages.A_FILE_WITH_THE_NAME_INFORMED_DOES_NOT_EXISTS;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_PLAYER_NAME;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE;
import static com.bowling.domain.utils.ErrorMessages.THE_FILE_INFORMED_IS_EMPTY;
import static com.bowling.domain.utils.ErrorMessages.YOU_NEED_TO_PASS_A_FILE_NAME_AS_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link FileValidator} unit test.
 *
 * @author Diego Peliser
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
public class FileValidatorUnitTest {

    @InjectMocks
    private FileValidator validator;

    @Test
    public void validateShouldThrowInvalidParameterExceptionIfArgsIsNull() {
        var exception = assertThrows(InvalidParameterException.class, () -> validator.validate((String[]) null));

        assertEquals(YOU_NEED_TO_PASS_A_FILE_NAME_AS_PARAMETER, exception.getMessage());
    }

    @Test
    public void validateShouldThrowInvalidParameterExceptionIfArgsIsEmpty() {
        var exception = assertThrows(InvalidParameterException.class, () -> validator.validate());

        assertEquals(YOU_NEED_TO_PASS_A_FILE_NAME_AS_PARAMETER, exception.getMessage());
    }

    @Test
    public void validateShouldThrowInvalidFileExceptionIfFileNotExists() {
        var exception = assertThrows(InvalidFileException.class, () -> validator.validate("fileDoesNotExists.txt"));

        assertEquals(A_FILE_WITH_THE_NAME_INFORMED_DOES_NOT_EXISTS, exception.getMessage());
    }

    @Test
    public void validateShouldThrowInvalidFileExceptionIfFileIsEmpty() {
        var exception = assertThrows(InvalidFileException.class,
            () -> validator.validate("src/test/resources/negative/empty.txt"));

        assertEquals(THE_FILE_INFORMED_IS_EMPTY, exception.getMessage());
    }

    @Test
    public void validateShouldNotThrowInvalidParameterExceptionIfArgsIsAValidFileName() {
        validator.validate("src/test/resources/positive/scores.txt");
    }

    @Test
    public void validateLineShouldThrowInvalidFileExceptionIfLineHasAnInvalidFormat() {
        var exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl\t"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("\t"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl2"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_FORMAT, exception.getMessage());
    }

    @Test
    public void validateLineShouldThrowInvalidFileExceptionIfLineHasAnInvalidPlayerName() {
        var exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl1\t2"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_PLAYER_NAME, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("\t2"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_PLAYER_NAME, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("23\t2"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_PLAYER_NAME, exception.getMessage());
    }

    @Test
    public void validateLineShouldThrowInvalidFileExceptionIfLineHasAnInvalidScore() {
        var exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl\tTest"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl\tTest1"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl\tX"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl\t/"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl\t1.1"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl\t11"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());

        exception = assertThrows(InvalidFileException.class, () -> validator.validateLine("Carl\t-7"));

        assertEquals(THE_FILE_INFORMED_HAS_A_LINE_WITH_AN_INVALID_SCORE, exception.getMessage());
    }

    @Test
    public void validateLineShouldNotThrowInvalidFileExceptionIfLineHasAValidFormat() {
        validator.validateLine("Carl\t0");
        validator.validateLine("Carl\t1");
        validator.validateLine("Carl\t2");
        validator.validateLine("Carl\t3");
        validator.validateLine("Carl\t4");
        validator.validateLine("Carl\t5");
        validator.validateLine("Carl\t6");
        validator.validateLine("Carl\t7");
        validator.validateLine("Carl\t8");
        validator.validateLine("Carl\t9");
        validator.validateLine("Carl\t10");
        validator.validateLine("Carl\tF");
    }

}
