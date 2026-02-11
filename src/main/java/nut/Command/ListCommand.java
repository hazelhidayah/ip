package nut.Command;

import nut.Task.TaskList;
import nut.Ui.Ui;

/**
 * Command that displays all tasks currently stored in the {@link nut.Task.TaskList}.
 * <p>
 * When executed, this command delegates to the {@link nut.Ui.Ui} to render the full task list
 * (including task indices and completion status, if applicable) and then returns control to the
 * application without terminating it.
 * </p>
 */
public class ListCommand implements Command {
    private final TaskList list; // list

    public ListCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public String execute(Ui ui) {
        return ui.showTaskList(list);
    }

}
