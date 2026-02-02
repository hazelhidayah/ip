package nut.Command;

import nut.Task.TaskList;
import nut.Ui.Ui;

/**
 * Command that ends the application session.
 * <p>
 * When executed, this command displays a goodbye message via the {@link Ui}
 * and signals to the caller that the program should terminate.
 * </p>
 */
public class DeleteCommand implements Command {
    private final TaskList list;
    private final int index;

    public DeleteCommand(TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public boolean execute(Ui ui) {
        ui.showTaskDeleted(list.get(index), list.size() - 1);
        list.delete(index);
        return false;
    }
}