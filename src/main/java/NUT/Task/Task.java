package NUT.Task;

/*
 * Class representing a generic task.
 * Other subtasks should be extended from this
 */

public class Task {
    // protected so it's accessible by subclasses
    protected final String name;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given name.
     * @param name The name/description of the task.
     * @throws NUTException If the name is null or empty.
     */
    public Task(String name) throws NUTException {
        if (name == null || name.trim().isEmpty()) { // handles empty/whitespace only input
            throw new NUTException("Task description cannot be empty!");
        }
        this.name = name;
        this.isDone = false; // default: task not done
    }

    public String getStatusIcon() {
        return (isDone ? "[x]" : "[ ]");
    }
    public void mark() {
        isDone = true;
    }
    public void unmark() {
        isDone = false;
    }
    public String getName() {
        return name;
    }

    // prints the task
    @Override
    public String toString() {
        return getStatusIcon() + " " + name;
    }
}
