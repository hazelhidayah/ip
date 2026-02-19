package nut.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import nut.exception.NutException;
import nut.task.Deadlines;
import nut.task.Events;
import nut.task.Task;
import nut.task.TaskList;
import nut.task.ToDos;
import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    void saveAndLoad_roundTrip_preservesTypeDoneStatusAndDateTime() throws Exception {
        Path tempFile = Files.createTempFile("nut-storage-roundtrip", ".txt");
        try {
            Storage storage = new Storage(tempFile.toString());
            TaskList original = new TaskList();

            ToDos todo = new ToDos("read notes");
            todo.mark();
            original.add(todo);

            Deadlines deadline = new Deadlines("submit report /by 15/09/2026 1800");
            original.add(deadline);

            Events event = new Events("project sync /from Mon 2pm /to Mon 3pm");
            event.mark();
            original.add(event);

            storage.save(original);
            ArrayList<Task> loaded = storage.load();

            assertEquals(3, loaded.size());

            Task loadedTodo = loaded.get(0);
            assertInstanceOf(ToDos.class, loadedTodo);
            assertTrue(loadedTodo.isDone());
            assertEquals("T | 1 | read notes", loadedTodo.toFileFormat());

            Task loadedDeadline = loaded.get(1);
            assertInstanceOf(Deadlines.class, loadedDeadline);
            assertFalse(loadedDeadline.isDone());
            assertEquals("D | 0 | submit report | 15/09/2026 1800", loadedDeadline.toFileFormat());

            Task loadedEvent = loaded.get(2);
            assertInstanceOf(Events.class, loadedEvent);
            assertTrue(loadedEvent.isDone());
            assertEquals("E | 1 | project sync | Mon 2pm | Mon 3pm", loadedEvent.toFileFormat());
        } catch (NutException e) {
            throw new AssertionError("Round-trip storage should not fail.", e);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}
