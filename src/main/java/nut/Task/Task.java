package nut.Task;

/**
 * Base class for all task types (ToDos, Deadlines, Events).
 * A task has a name/description and a completion status.
 */
public class Task {
    /** The name/description of the task. */
    protected final String name;

    /** Indicates whether the task has been completed. */
    protected boolean isDone;

    /**
     * Constructs a new Task with the given name.
     * The task is initially marked as not done.
     * @param name The name/description of the task.
     */
    public Task(String name) throws NutException {
        if (name == null || name.trim().isEmpty()) {
            throw new NutException("""
                    ____________________________________________________________
                    OOPS! The description of a task cannot be empty.
                    ____________________________________________________________
                    """);
        }
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for loading tasks from file with a completion status.
     * @param name The name/description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "[x]" if a task is done, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[x]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the name/description of the task.
     *
     * @return The task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation for saving to file.
     * This should be overridden by subclasses for specific formats.
     *
     * @return The file format string.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A formatted string showing the status icon and task name.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + name;
    }
}