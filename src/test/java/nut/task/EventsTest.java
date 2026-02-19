package nut.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nut.exception.NutException;
import org.junit.jupiter.api.Test;

public class EventsTest {

    @Test
    void constructor_validFormat_parsesNameAndTimesCorrectly() throws NutException {
        Events event = new Events("project sync /from Mon 2pm /to Mon 3pm");
        assertEquals("project sync", event.getName());
        String eventText = event.toString();
        assertTrue(eventText.startsWith("[E] [ ] project sync"));
        assertTrue(eventText.contains("Mon 2pm"));
        assertTrue(eventText.contains("Mon 3pm"));
    }

    @Test
    void constructor_invalidFormat_throwsNutException() {
        assertThrows(NutException.class, () -> new Events("project sync /from Mon 2pm"));
        assertThrows(NutException.class, () -> new Events("project sync /at Mon 2pm /to Mon 3pm"));
        assertThrows(NutException.class, () -> new Events("project sync /from Mon 2pm /until Mon 3pm"));
    }

    @Test
    void constructor_invalidFormat_containsUsageHint() {
        NutException exception = assertThrows(NutException.class, () ->
                new Events("project sync /at Mon 2pm /until Mon 3pm"));
        assertTrue(exception.getMessage().contains("event <name> /from <start> /to <end>"));
    }

    @Test
    void loadConstructor_andToFileFormat_preserveFields() {
        Events event = new Events("project sync", "Mon 2pm", "Mon 3pm", true);
        assertTrue(event.isDone());
        assertEquals("E | 1 | project sync | Mon 2pm | Mon 3pm", event.toFileFormat());
    }
}
