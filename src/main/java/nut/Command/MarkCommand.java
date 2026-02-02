package nut.Command;

import nut.Task.TaskList;
import nut.Ui.Ui;

/**
 * Command that marks a task in the {@link nut.Task.TaskList} as completed.
 * <p>
 * This command targets a task by its index. When executed, it validates that the index is within
 * bounds, marks the corresponding task as done, and uses the {@link nut.Ui.Ui} to display the
 * updated task state (or an error message if the index is invalid).
 * </p>
 */
public class MarkCommand implements Command {
    private final TaskList list;
    private final int index;

    // constructor
    public MarkCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public boolean execute(Ui ui) {
        if (index < 0 || index >= list.size()) { // out of bound
            ui.showSearchError();
            return false;
        }
        list.get(index).mark(); // mark() is from Task
        ui.markTask(list, index);
        return false;
    }
}