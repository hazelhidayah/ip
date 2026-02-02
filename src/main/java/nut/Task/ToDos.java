package nut.Task;

/**
 * Represents a todo task (a task without any associated date/time).
 */
public class ToDos extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param name The description of the todo task.
     * @throws NutException If {@code name} is {@code null} or blank.
     */
    public ToDos(String name) throws NutException {
        super(name);
    }

    /**
     * Returns the status icon for this todo task.
     *
     * @return {@code "[T] [x]"} if done, otherwise {@code "[T] [ ]"}.
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[T] [x]" : "[T] [ ]");
    }
}