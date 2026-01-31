package NUT.Task;

/*
 * Class representing a generic task.
 * Other subtasks should be extended from this
 */

public class Task {
    protected final String name; // protected so it's accessible by subclasses
    protected boolean isDone;

    // constructor
    public Task(String name) {
        if (name == null || name.trim().isEmpty()) { // handles empty/whitespace only input
            System.out.println("The description cannot be empty :(");
        }
        this.name = name;
        this.isDone = false; // default: task not done
    }

    // constructor for loading a file
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
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

    /**
     * Converts the task to file format for saving.
     * Format: T | 0/1 | description
     * where 0 = not done, 1 = done
     * @return String representation in file format
     */

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    // prints the task
    @Override
    public String toString() {
        return getStatusIcon() + " " + name;
    }
}