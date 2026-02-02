package nut.Command;

import nut.Task.Task;
import nut.Task.TaskList;
import nut.Ui.Ui;

/**
 * Command that adds a {@link Task} to a {@link TaskList}.
 * <p>
 * When executed, this command appends the task to the given list and uses the
 * {@link Ui} to display a confirmation message (including the updated list size).
 * </p>
 */
public class AddCommand implements Command {
    private final TaskList list;
    private final Task task;

    public AddCommand(TaskList list, Task task) {
        this.list = list;
        this.task = task;
    }

    @Override
    public boolean execute(Ui ui) {
        list.add(task);
        ui.showTaskAdded(task, list.size());
        return false;
    }
}