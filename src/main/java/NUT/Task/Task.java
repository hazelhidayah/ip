package NUT.Task;

/**
 * Base class for all task types (e.g. {@link ToDos}, {@link Deadlines}, {@link Events}).
 * A task has a description and a completion status.
 */
public class Task {
    /**
     * The name/description of the task.
     */
    protected final String name;

    /**
     * Indicates whether the task has been completed.
     */
    protected boolean isDone;

    /**
     * Constructs a new task with the given description.
     * The task is initially marked as not done.
     *
     * @param name the task description
     * @throws NUTException if {@code name} is {@code null} or blank
     */
    public Task(String name) throws NUTException {
        if (name == null || name.trim().isEmpty()) {
            throw new NUTException("""
                    ____________________________________________________________
                    OOPS! The description of a task cannot be empty.
                    ____________________________________________________________
                    """);
        }
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a new task with the given description and completion status.
     * Intended for restoring tasks from storage.
     *
     * @param name   the task description
     * @param isDone the completion status
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return {@code "[x]"} if a task is done, {@code "[ ]"} otherwise
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
     * Returns the task description.
     *
     * @return the task description
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return {@code true} if the task is done; {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation for saving to file.
     * Subclasses should override this for type-specific formats.
     *
     * @return the file format string
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    /**
     * Returns a human-readable representation of this task.
     *
     * @return a formatted string showing the status icon and task description
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + name;
    }
}