package nut.command;

import nut.task.TaskList;
import nut.ui.Ui;

/**
 * command that ends the application session.
 * <p>
 * When executed, this command displays a goodbye message via the {@link Ui}
 * and signals to the caller that the program should terminate.
 * </p>
 */
public class DeleteCommand implements Command {
    private final TaskList list;
    private final int index;

    public DeleteCommand(TaskList list, int index) {
        assert list != null : "TaskList must not be null";
        assert index >= 0 && index < list.size() : "index out of bounds: " + index;
        this.list = list;
        this.index = index;
    }

    @Override
    public String execute(Ui ui) {
        String response = ui.showTaskDeleted(list.get(index), list.size() - 1);
        list.delete(index);
        return response;
    }
}
