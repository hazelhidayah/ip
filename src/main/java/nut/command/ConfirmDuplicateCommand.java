package nut.command;

import nut.task.Task;
import nut.task.TaskList;
import nut.ui.Ui;

/**
 * Handles confirmation for adding a duplicate task.
 */
public class ConfirmDuplicateCommand implements Command {
    private final TaskList list;
    private final boolean shouldAdd;

    public ConfirmDuplicateCommand(TaskList list, boolean shouldAdd) {
        assert list != null : "TaskList must not be null";
        this.list = list;
        this.shouldAdd = shouldAdd;
    }

    @Override
    public String execute(Ui ui) {
        Task task = list.resolvePendingDuplicate(shouldAdd);
        if (shouldAdd) {
            return ui.showTaskAdded(task, list.size());
        }
        return ui.showDuplicateTaskCancelled(task);
    }
}
