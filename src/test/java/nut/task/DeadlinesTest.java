package nut.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nut.exception.NutException;
import org.junit.jupiter.api.Test;
import java.util.Locale;

public class DeadlinesTest {

    @Test
    void constructor_validDateTime_parsesAndFormatsCorrectly() throws NutException {
        Deadlines deadline = new Deadlines("submit report /by 15/09/2026 1800");
        assertEquals("submit report", deadline.getName());
        String expected = "Sep 15 2026, 6:00 PM";
        assertEquals(expected.toLowerCase(Locale.ROOT), deadline.getDeadline().toLowerCase(Locale.ROOT));
        assertEquals("D | 0 | submit report | 15/09/2026 1800", deadline.toFileFormat());
    }

    @Test
    void constructor_validDateOnly_parsesAndFormatsCorrectly() throws NutException {
        Deadlines deadline = new Deadlines("submit report /by 15/09/2026");
        assertEquals("submit report", deadline.getName());
        assertEquals("Sep 15 2026", deadline.getDeadline());
        assertEquals("D | 0 | submit report | 15/09/2026", deadline.toFileFormat());
    }

    @Test
    void constructor_invalidFormat_throwsNutException() {
        assertThrows(NutException.class, () -> new Deadlines("submit report by 15/09/2026"));
        assertThrows(NutException.class, () -> new Deadlines("/by 15/09/2026"));
    }

    @Test
    void constructor_invalidDate_throwsNutExceptionWithFormatHint() {
        NutException exception = assertThrows(NutException.class, () ->
                new Deadlines("submit report /by 2026-09-15"));
        assertTrue(exception.getMessage().contains("deadline <name> /by"));
    }

    @Test
    void loadConstructor_preservesDoneStatus() throws NutException {
        Deadlines deadline = new Deadlines("submit report", "15/09/2026 1800", true);
        assertTrue(deadline.isDone());
        assertEquals("D | 1 | submit report | 15/09/2026 1800", deadline.toFileFormat());
    }
}
