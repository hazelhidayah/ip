package nut.command;

import nut.task.TaskList;
import nut.ui.Ui;

/**
 * Command that unmarks a task in the {@link nut.task.TaskList} as not completed.
 * <p>
 * This command targets a task by its index. When executed, it validates that the index is within
 * bounds, unmarks the corresponding task as not done, and uses the {@link nut.ui.Ui} to display
 * the updated task state (or an error message if the index is invalid).
 * </p>
 */
public class UnmarkCommand implements Command {
    private final TaskList list;
    private final int index;

    public UnmarkCommand(TaskList list, int index) {
        assert list != null : "TaskList must not be null";
        this.list = list;
        this.index = index;
    }

    @Override
    public String execute(Ui ui) {
        if (index < 0 || index >= list.size()) { // out of bound
            return ui.showInvalidTaskIndex(list.size());
        }
        list.get(index).unmark(); // unmark() is from task
        return ui.markTask(list, index);
    }
}
