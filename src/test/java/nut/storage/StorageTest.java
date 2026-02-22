package nut.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
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

    @Test
    void saveAndLoad_roundTrip_preservesUnicodeCharacters() throws Exception {
        Path tempFile = Files.createTempFile("nut-storage-unicode", ".txt");
        try {
            Storage storage = new Storage(tempFile.toString());
            TaskList original = new TaskList();

            original.add(new ToDos("学习中文"));
            original.add(new Deadlines("完成实验 /by 31/12/2027"));

            storage.save(original);
            String savedContent = Files.readString(tempFile, StandardCharsets.UTF_8);
            assertTrue(savedContent.contains("学习中文"));
            assertTrue(savedContent.contains("完成实验"));

            ArrayList<Task> loaded = storage.load();
            assertEquals(2, loaded.size());
            assertEquals("T | 0 | 学习中文", loaded.get(0).toFileFormat());
            assertEquals("D | 0 | 完成实验 | 31/12/2027", loaded.get(1).toFileFormat());
        } catch (NutException e) {
            throw new AssertionError("Unicode round-trip storage should not fail.", e);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void load_deadlineDescriptionContainingDelimiter_preservesActualDeadline() throws Exception {
        Path tempFile = Files.createTempFile("nut-storage-deadline-delimiter", ".txt");
        try {
            Files.writeString(tempFile, "D | 0 | inject | 01/01/2026 0000 | 31/12/2027\n", StandardCharsets.UTF_8);
            Storage storage = new Storage(tempFile.toString());

            ArrayList<Task> loaded = storage.load();
            assertEquals(1, loaded.size());

            Task loadedTask = loaded.get(0);
            assertInstanceOf(Deadlines.class, loadedTask);
            Deadlines loadedDeadline = (Deadlines) loadedTask;
            assertEquals("inject | 01/01/2026 0000", loadedDeadline.getName());
            assertEquals("D | 0 | inject | 01/01/2026 0000 | 31/12/2027", loadedDeadline.toFileFormat());
        } catch (NutException e) {
            throw new AssertionError("Delimiter-safe deadline loading should not fail.", e);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}
