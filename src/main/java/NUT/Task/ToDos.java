package NUT.Task;

/**
 * Represents a simple to-do task with only a description and a completion state.
 *
 * <p>A {@code ToDos} task does not have any associated date/time information (unlike
 * {@link Deadlines} and {@link Events}). It inherits the task description and completion
 * behaviour (mark/unmark) from {@link Task} and customises the status icon to indicate the
 * to-do task type.</p>
 */
public class ToDos extends Task {

    /**
     * Creates a new to-do task with the given description.
     *
     * @param name description of the to-do task; must not be {@code null} or blank
     * @throws NUTException if {@code name} is {@code null} or blank
     **/
    public ToDos(String name) throws NUTException {
        super(name);
    }

    // included [T]
    @Override
    public String getStatusIcon() {
        return (isDone ? "[T] [x]" : "[T] [ ]");
    }
}