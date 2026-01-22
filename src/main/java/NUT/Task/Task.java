package NUT.Task;

/*
 * Abstract class representing a generic task.
 */

public class Task {
    private final String name;
    private boolean isDone;

    // constructor
    public Task(String name) {
        this.name = name;
        this.isDone = false; // default: task not done
    }

    public String getStatusIcon() { return (isDone ? "[x]" : "[ ]");}
    public void mark() { isDone = true; }
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
