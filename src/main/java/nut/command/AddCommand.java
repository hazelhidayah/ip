package nut.command;

import nut.task.Task;
import nut.task.TaskList;
import nut.ui.Ui;

/**
 * command that adds a {@link Task} to a {@link TaskList}.
 * <p>
 * When executed, this command appends the task to the given list and uses the
 * {@link Ui} to display a confirmation message (including the updated list size).
 * </p>
 */
public class AddCommand implements Command {
    private final TaskList list;
    private final Task task;

    public AddCommand(TaskList list, Task task) {
        assert list != null : "TaskList must not be null";
        assert task != null : "Task must not be null";
        this.list = list;
        this.task = task;
    }

    @Override
    public String execute(Ui ui) {
        list.add(task);
        return ui.showTaskAdded(task, list.size());
    }
}
