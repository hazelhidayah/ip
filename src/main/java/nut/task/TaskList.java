package nut.task;

import java.util.*;

/**
 * Represents a list of {@link Task} objects.
 * <p>
 * {@code TaskList} provides basic operations for adding, retrieving, deleting, and
 * querying the number of tasks.
 */
public class TaskList {
    // Internal list that stores tasks.
    private final ArrayList<Task> list;

    // To keep track of current inputs
    private final TreeSet<String> set;

    // To check if there is a current duplicate task pending confirmation
    private Task pendingDuplicate;

    // Constructs an empty {@code TaskList}.
    public TaskList() {
        this.list = new ArrayList<>();
        this.set = new TreeSet<>();
    }

    /**
     * Constructs a {@code TaskList} with an existing list of tasks (typically used when loading from storage).
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
        this.set = new TreeSet<>(); // To keep track of current tasks.
        for (Task task : list) { // for each task in the list...
            set.add(normalizeName(task.getName()));
        }
    }

    /**
     * Adds a task to this task list.
     *
     * @param task The task to add.
     * @return True if the task was added, false if it was queued as a duplicate pending confirmation.
     */
    public boolean add(Task task) {
        String taskName = normalizeName(task.getName());
        if (set.contains(taskName)) {
            pendingDuplicate = task;
            return false; // duplicate pending confirmation
        }
        addToListAndSet(task); // adding if not a duplicate
        return true;
    }

    /**
     * Returns true if there is a pending duplicate task awaiting confirmation.
     */
    public boolean hasPendingDuplicate() {
        return pendingDuplicate != null;
    }

    /**
     * Confirms or cancels adding the pending duplicate task.
     *
     * @param shouldAdd True to add the task, false to cancel.
     * @return The pending task, or null if there was none.
     */
    public Task resolvePendingDuplicate(boolean shouldAdd) {
        if (pendingDuplicate == null) {
            return null;
        }
        Task task = pendingDuplicate;
        pendingDuplicate = null;
        if (shouldAdd) {
            addToListAndSet(task);
        }
        return task;
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
        Task removed = list.remove(index);
        set.remove(normalizeName(removed.getName()));
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

    /**
     * Returns the name of the task after normalisation to lowercase without whitespace.
     *
     * @return A String with the normalised name of the task.
     */
    private String normalizeName(String name) {
        return name.trim().toLowerCase(Locale.ROOT);
    }

    private void addToListAndSet(Task task) {
        list.add(task);
        set.add(normalizeName(task.getName()));
    }
}
