package nut.Command;

import nut.Task.TaskList;
import nut.Ui.Ui;

/**
 * Command that unmarks a task in the {@link nut.Task.TaskList} as uncompleted.
 * <p>
 * This command targets a task by its index. When executed, it validates that the index is within
 * bounds, marks the corresponding task as done, and uses the {@link nut.Ui.Ui} to display the
 * updated task state (or an error message if the index is invalid).
 * </p>
 */
public class UnmarkCommand implements Command {
    private final TaskList list;
    private final int index;

    public UnmarkCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public String execute(Ui ui) {
        if (index < 0 || index >= list.size()) { // out of bound
            return ui.showSearchError();
        }
        list.get(index).unmark(); // unmark() is from Task
        return ui.markTask(list, index);
    }
}
