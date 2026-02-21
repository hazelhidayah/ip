package nut.command;

import nut.task.TaskList;
import nut.ui.Ui;

/**
 * Command that deletes a task from the {@link nut.task.TaskList}.
 * <p>
 * This command targets a task by its index. When executed, it validates that the
 * index is within bounds, removes the corresponding task, and uses the
 * {@link Ui} to display the deletion result (or an error message if the index is invalid).
 * </p>
 */
public class DeleteCommand implements Command {
    private final TaskList list;
    private final int index;

    public DeleteCommand(TaskList list, int index) {
        assert list != null : "TaskList must not be null";
        this.list = list;
        this.index = index;
    }

    @Override
    public String execute(Ui ui) {
        if (index < 0 || index >= list.size()) { // out of bound
            return ui.showInvalidTaskIndex(list.size());
        }
        String response = ui.showTaskDeleted(list.get(index), list.size() - 1);
        list.delete(index);
        return response;
    }
}
