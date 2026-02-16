package nut.command;

import nut.task.TaskList;
import nut.ui.Ui;

/**
 * command that marks a task in the {@link nut.task.TaskList} as completed.
 * <p>
 * This command targets a task by its index. When executed, it validates that the index is within
 * bounds, marks the corresponding task as done, and uses the {@link nut.ui.Ui} to display the
 * updated task state (or an error message if the index is invalid).
 * </p>
 */
public class MarkCommand implements Command {
    private final TaskList list;
    private final int index;

    public MarkCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public String execute(Ui ui) {
        if (index < 0 || index >= list.size()) { // out of bound
            return ui.showSearchError();
        }
        list.get(index).mark(); // mark() is from task
        return ui.markTask(list, index);
    }
}
