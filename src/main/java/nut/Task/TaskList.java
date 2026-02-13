package nut.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a list of {@link Task} objects.
 * <p>
 * {@code TaskList} provides basic operations for adding, retrieving, deleting, and
 * querying the number of tasks.
 */
public class TaskList {
    // Internal backing list that stores tasks.
    private final ArrayList<Task> list;

    // Constructs an empty {@code TaskList}.
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an existing list of tasks (typically used when loading from storage).
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Adds a task to this task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index Index of the task to retrieve (0-based).
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If {@code index} is out of range.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Index of the task to delete (0-based).
     * @throws IndexOutOfBoundsException If {@code index} is out of range.
     */
    public void delete(int index) {
        list.remove(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the underlying list containing all tasks.
     * <p>
     * The returned list is an unmodifiable view; modifying it will throw an exception.
     *
     * @return An unmodifiable {@link List} view of the tasks.
     */
    public List<Task> retrieveAll() {
        return Collections.unmodifiableList(list);
    }
}