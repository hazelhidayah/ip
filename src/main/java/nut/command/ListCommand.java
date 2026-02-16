package nut.command;

import nut.task.TaskList;
import nut.ui.Ui;

/**
 * command that displays all tasks currently stored in the {@link nut.task.TaskList}.
 * <p>
 * When executed, this command delegates to the {@link nut.ui.Ui} to render the full task list
 * (including task indices and completion status, if applicable) and then returns control to the
 * application without terminating it.
 * </p>
 */
public class ListCommand implements Command {
    private final TaskList list; // list

    public ListCommand(TaskList list) {
        assert list != null : "TaskList must not be null";
        this.list = list;
    }

    @Override
    public String execute(Ui ui) {
        return ui.showTaskList(list);
    }

}
