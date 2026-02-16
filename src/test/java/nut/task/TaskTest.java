package nut.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void markAndUnmark_updatesDoneStatusAndIcon() throws NutException {

        // Arrange
        Task task = new Task("read book");

        // Act & Assert
        assertFalse(task.isDone());
        assertEquals("[ ]", task.getStatusIcon());

        // Act & Assert
        task.mark();
        assertTrue(task.isDone());
        assertEquals("[x]", task.getStatusIcon());

        // Act & Assert
        task.unmark();
        assertFalse(task.isDone());
        assertEquals("[ ]", task.getStatusIcon());
    }

    @Test
    void toFileFormat_includesTypeStatusAndName() throws NutException {
        // Arrange
        Task task = new Task("sleep");
        assertEquals("T | 0 | sleep", task.toFileFormat());

        // Act & Assert
        task.mark();
        assertEquals("T | 1 | sleep", task.toFileFormat());
    }

    @Test
    void constructor_nullOrBlank_throwsNutException() {
        assertThrows(NutException.class, () -> new Task(null));
        assertThrows(NutException.class, () -> new Task(""));
        assertThrows(NutException.class, () -> new Task("   "));
    }
}